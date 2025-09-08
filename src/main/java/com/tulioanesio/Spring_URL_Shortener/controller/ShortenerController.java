package com.tulioanesio.Spring_URL_Shortener.controller;

import com.tulioanesio.Spring_URL_Shortener.dtos.ShortenerUrlRequest;
import com.tulioanesio.Spring_URL_Shortener.dtos.ShortenerUrlResponse;
import com.tulioanesio.Spring_URL_Shortener.models.Shortener;
import com.tulioanesio.Spring_URL_Shortener.services.ShortenerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;

@RestController
@RequestMapping
public class ShortenerController {

    private final ShortenerService shortenerService;

    public ShortenerController(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<ShortenerUrlResponse> createShortUrl(@RequestBody @Valid ShortenerUrlRequest request, HttpServletRequest servletRequest) {

        Shortener newShortener = shortenerService.createAndSaveShortUrl(request.originalUrl());

        String fullShortUrl = ServletUriComponentsBuilder.fromContextPath(servletRequest)
                .path("/dev/{shortCode}")
                .buildAndExpand(newShortener.getShortUrl())
                .toUriString();

        var response = new ShortenerUrlResponse(request.originalUrl(), fullShortUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Object> redirect(@PathVariable String shortCode) {
        return shortenerService.findOriginalUrlByShortCode(shortCode)
                .map(shortener -> ResponseEntity.status(HttpStatus.FOUND)
                        .location(URI.create(shortener.getOriginalUrl()))
                        .build())
                        .orElse(ResponseEntity.notFound().build());
    }
}