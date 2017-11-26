package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.entity.Media;
import org.jugistanbul.secondopinion.api.exception.MediaIOException;
import org.jugistanbul.secondopinion.api.service.MediaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@RestController
@RequestMapping("/v1/download")
public class MediaDownloadController {

    private MediaService mediaService;

    public MediaDownloadController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @RequestMapping("/{fileName}")
    public void download(HttpServletResponse response, @PathVariable("fileName") String fileName) {


        Media media = mediaService.get(fileName);

        InputStream fileInputStream = mediaService.getFileInputStream(fileName);

        response.setHeader("Content-Type", media.getType() == null || media.getType().isEmpty() ? "application/octet-stream" : media.getType());
        response.setStatus(HttpStatus.OK.value());

        try {
            int bytesRead = 0;
            byte[] buffer = new byte[2048];
            while ((bytesRead = fileInputStream.read(buffer)) > 0) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }
        } catch (IOException ex) {
            throw new MediaIOException("media.read");
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                throw new MediaIOException("media.read");
            }
        }
    }
}
