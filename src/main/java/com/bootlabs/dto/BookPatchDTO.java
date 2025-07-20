package com.bootlabs.dto;

import java.math.BigDecimal;
import java.util.Optional;

public record BookPatchDTO(
        Optional<String> title,
        Optional<String> isbn,
        Optional<String> description,
        Optional<Integer> page,
        Optional<BigDecimal> price
) {}