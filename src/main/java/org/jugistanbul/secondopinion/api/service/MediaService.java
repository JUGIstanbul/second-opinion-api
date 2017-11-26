package org.jugistanbul.secondopinion.api.service;

import org.jugistanbul.secondopinion.api.configuration.properties.SecondOpinionConfigProperties;
import org.jugistanbul.secondopinion.api.entity.Media;
import org.jugistanbul.secondopinion.api.entity.ModelStatus;
import org.jugistanbul.secondopinion.api.exception.EntityNotFoundException;
import org.jugistanbul.secondopinion.api.exception.MediaIOException;
import org.jugistanbul.secondopinion.api.repository.MediaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Service
@Transactional
public class MediaService {

    private MediaRepository mediaRepository;
    private SecondOpinionConfigProperties appConfig;

    public MediaService(MediaRepository mediaRepository, SecondOpinionConfigProperties appConfig) {
        this.mediaRepository = mediaRepository;
        this.appConfig = appConfig;
    }

    public Media createMedia(String baseUrl, InputStream in) {

        Path path = Paths.get(appConfig.getMediaUploadPath());
        FileOutputStream out = null;
        try {
            Files.createDirectories(path);
            byte[] buffer = new byte[2048];
            File file = path.resolve(UUID.randomUUID().toString()).toFile();
            out = new FileOutputStream(file);
            int readBytes;
            while ((readBytes = in.read(buffer)) > 0) {
                out.write(buffer, 0, readBytes);
            }

            Media media = new Media();
            media.setFileName(file.getName());
            media.setUrl(baseUrl.substring(0, baseUrl.lastIndexOf('/')) + "/download/" + file.getName());

            return mediaRepository.save(media);
        } catch (IOException ex) {
            throw new MediaIOException("media.persist", ex);
        } finally {
            try {
                in.close();
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                throw new MediaIOException("media.persist", e);
            }
        }
    }

    public Media get(String fileName) {

        Media media = mediaRepository.findByFileName(fileName);

        if (media == null) {
            throw new EntityNotFoundException("entity.notFound");
        }

        return media;
    }

    public InputStream getFileInputStream(String fileName) {

        Media media = mediaRepository.findByFileName(fileName);

        if (media == null) {
            throw new EntityNotFoundException("entity.notFound");
        }

        try {
            return new FileInputStream(Paths.get(appConfig.getMediaUploadPath(), media.getFileName()).toFile());
        } catch (FileNotFoundException e) {
            throw new MediaIOException("media.read");
        }
    }

    public Media get(Long id) {

        Media media = mediaRepository.findOne(id);

        if (media == null) {
            throw new EntityNotFoundException("entity.notFound");
        }
        return media;
    }

    public void delete(Long id) {
        Media media = get(id);

        media.setModelStatus(ModelStatus.DELETED);
        mediaRepository.save(media);
    }
}
