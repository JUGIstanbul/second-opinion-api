package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.entity.Media;
import org.jugistanbul.secondopinion.api.exception.MediaIOException;
import org.jugistanbul.secondopinion.api.service.MediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@RestController
@RequestMapping("/v1/media")
public class MediaController {

    private MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Media> post(HttpServletRequest request) {
        try {
            String fullRequestUrl = UrlHelper.getFullRequestUrl(request);
            Media media = mediaService.createMedia(fullRequestUrl, request.getPart("file").getInputStream());
            return ResponseEntity.created(URI.create(fullRequestUrl + "/" + media.getId())).body(media);
        } catch (ServletException | IOException e) {
            throw new MediaIOException("media.persist");
        }
    }

    @GetMapping("/{id}")
    public Media get(@PathVariable Long id) {
        return mediaService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        mediaService.delete(id);
    }
}
