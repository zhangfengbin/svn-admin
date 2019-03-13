package com.pfpj.exception;

import org.springframework.http.HttpStatus;

public class PFPJException extends RuntimeException  {
    private HttpStatus httpStatus;
    private int errorCode;
    public PFPJException(HttpStatus httpStatus, int errorCode, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;

        // TODO Auto-generated constructor stub
    }

    public PFPJException(String message, int errorCode, Exception e) {

        super(message, e.getCause());
        // TODO Auto-generated constructor stub
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
    public int getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }


}
