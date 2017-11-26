package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.dto.Response;
import org.jugistanbul.secondopinion.api.dto.enumtype.RequestStatus;
import org.jugistanbul.secondopinion.api.exception.EntityNotFoundException;
import org.jugistanbul.secondopinion.api.exception.EntityValidationException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    private MessageSource messageSource;

    public ControllerExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response handleNotFoundException(HttpServletRequest request, Exception exception) {

        return createLocalizedResponse(exception);
    }

    @ExceptionHandler(EntityValidationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Response handleValidationException(HttpServletRequest request, Exception exception) {

        return createLocalizedResponse(exception);
    }

    private Response createLocalizedResponse(Exception exception) {
        Response response = new Response();

        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage(exception.getMessage(), null, locale);
        String[] split = message.split(";");
        response.setErrorCode(split[0]);
        response.setErrorMessage(split[1]);

        response.setStatus(RequestStatus.FAILURE);
        return response;
    }
}
