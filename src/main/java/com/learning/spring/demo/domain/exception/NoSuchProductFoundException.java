package com.learning.spring.demo.domain.exception;

public class NoSuchProductFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NoSuchProductFoundException(String message) {
        super(message);
    }

}
