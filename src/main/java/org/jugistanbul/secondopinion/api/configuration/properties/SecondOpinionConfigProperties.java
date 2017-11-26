package org.jugistanbul.secondopinion.api.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Component
@ConfigurationProperties(value = "api")
public class SecondOpinionConfigProperties {

    private String mediaUploadPath;

    public String getMediaUploadPath() {
        return mediaUploadPath;
    }

    public void setMediaUploadPath(String mediaUploadPath) {
        this.mediaUploadPath = mediaUploadPath;
    }
}
