package com.tulioanesio.Spring_URL_Shortener.controller;

import com.tulioanesio.Spring_URL_Shortener.dtos.ShortenerUrlRequest;
import com.tulioanesio.Spring_URL_Shortener.dtos.ShortenerUrlResponse;
import com.tulioanesio.Spring_URL_Shortener.models.Shortener;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import jakarta.validation.Valid;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import software.amazon.awssdk.enhanced.dynamodb.Key;

import java.net.URI;

@RestController
@RequestMapping
public class ShortenerController {

    private final DynamoDbTemplate dynamoDbTemplate;

    public ShortenerController(DynamoDbTemplate dynamoDbTemplate) {
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    @PostMapping("/shorten")
    public ResponseEntity<ShortenerUrlResponse> createShortUrl(@RequestBody @Valid ShortenerUrlRequest request) {

        String shortCode = RandomStringUtils.randomAlphanumeric(6);

        Shortener newShortener = new Shortener(shortCode, request.originalUrl());

        dynamoDbTemplate.save(newShortener);

        String fullShortUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{shortCode}")
                .buildAndExpand(shortCode)
                .toUriString();

        var response = new ShortenerUrlResponse(request.originalUrl(), fullShortUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        Key key = Key.builder().partitionValue(shortCode).build();

        Shortener foundShortener = dynamoDbTemplate.load(key, Shortener.class);

        if (foundShortener != null) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(foundShortener.getOriginalUrl()))
                    .build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}