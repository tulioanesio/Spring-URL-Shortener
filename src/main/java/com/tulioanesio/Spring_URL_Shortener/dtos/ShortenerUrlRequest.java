package com.tulioanesio.Spring_URL_Shortener.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record ShortenerUrlRequest(
        @NotBlank
        @URL
        String originalUrl) {
}
