package org.jugistanbul.secondopinion.api.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogExecutionInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LogExecutionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                request.setAttribute("requestStartTime", System.currentTimeMillis());
                logger.info("Starting controller method for {}.{}",
                        handlerMethod.getBean().getClass().getSimpleName(),
                        handlerMethod.getMethod().getName());
            }
        } catch (Exception e) {
            logger.error("Caught an exception while executing handler method", e);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        try {
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                logger.info("Completed controller method for {}.{} takes {} ms",
                        handlerMethod.getBean().getClass().getSimpleName(),
                        handlerMethod.getMethod().getName(),
                        (System.currentTimeMillis() - (Long) request.getAttribute("requestStartTime")));
            }
        } catch (Exception e) {
            logger.error("Caught an exception while executing handler method", e);
        }
    }
}
