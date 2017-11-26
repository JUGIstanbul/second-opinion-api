package org.jugistanbul.secondopinion.api.controller;

import javax.servlet.http.HttpServletRequest;

public class UrlHelper {
    public static String getFullRequestUrl(HttpServletRequest request) {
        String scheme = request.getScheme() + "://";
        String serverName = request.getServerName();
        String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
        String contextPath = request.getContextPath();
        return scheme + serverName + serverPort + request.getRequestURI();
    }
}
