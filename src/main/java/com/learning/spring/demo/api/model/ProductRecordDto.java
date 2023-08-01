package com.learning.spring.demo.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductRecordDto(
        @NotBlank String name,
        @NotNull BigDecimal value,
        BigDecimal discountRate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

        ) {
}
