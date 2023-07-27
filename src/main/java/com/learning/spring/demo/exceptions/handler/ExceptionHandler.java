package com.learning.spring.demo.exceptions.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NoSuchProductFoundException.class)
    public ResponseEntity<Object> handleNoSuchProductFound(
            NoSuchProductFoundException exception, WebRequest request) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ExceptionResponseMessage exceptionResponseMessage =
                new ExceptionResponseMessage(
                        httpStatus.value(),
                        LocalDateTime.now(),
                        exception.getMessage()
                );

        return handleExceptionInternal(
                exception,
                exceptionResponseMessage,
                new HttpHeaders(),
                httpStatus,
                request
        );
    }


}
