package com.pfpj.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class PFPJExceptionHandler extends ResponseEntityExceptionHandler {
    //处理自定义异常
    @ExceptionHandler(PFPJException.class)
    public ResponseEntity<Object> handleCustomerException(PFPJException ex) {
        final ErrorDTO customeError = new ErrorDTO(ex.getErrorCode(), ex.getLocalizedMessage());
        return new ResponseEntity<Object>(customeError, new HttpHeaders(), ex.getHttpStatus());
    }

    //处理通用异常，这里举例说明如何覆盖处理 请求方法不支持的异常
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        // TODO Auto-generated method stub
        final ErrorDTO customeError = new ErrorDTO(status.value(), "HttpRequestMethodNotSupported");
        return new ResponseEntity<Object>(customeError, new HttpHeaders(), status);
    }


}
