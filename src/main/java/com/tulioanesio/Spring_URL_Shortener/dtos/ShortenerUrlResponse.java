package com.tulioanesio.Spring_URL_Shortener.dtos;

public record ShortenerUrlResponse(
        String originalUrl,
        String shortUrl) {
}
