package org.jugistanbul.secondopinion.api.dto;

import org.jugistanbul.secondopinion.api.dto.enumtype.RequestStatus;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class Response {

    private RequestStatus status;
    private String errorCode;
    private String errorMessage;

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
