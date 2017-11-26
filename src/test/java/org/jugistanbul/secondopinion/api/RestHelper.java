package org.jugistanbul.secondopinion.api;

import java.net.URI;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class RestHelper {
    public static String extractIdStringFromURI(URI location) {
        String[] split = location.getPath().split("\\/");
        return split[split.length - 1];
    }

    public static Long extractIdFromURI(URI location) {
        return Long.valueOf(extractIdStringFromURI(location));
    }

}
