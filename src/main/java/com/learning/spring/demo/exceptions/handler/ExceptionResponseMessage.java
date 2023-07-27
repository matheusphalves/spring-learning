package com.learning.spring.demo.exceptions.handler;

import java.time.LocalDateTime;

public record ExceptionResponseMessage(Integer status, LocalDateTime date, String message) {

}
