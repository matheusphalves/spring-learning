package com.learning.spring.demo.api.exceptionhandler;

import java.time.LocalDateTime;

public record ExceptionResponseMessage(Integer status, LocalDateTime date, String message) {

}
