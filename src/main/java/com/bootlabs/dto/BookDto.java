package com.bootlabs.dto;

import java.math.BigDecimal;

public record BookDto(
        Long id,
        String title,
        String isbn,
        String description,
        Integer page,
        BigDecimal price
) {}