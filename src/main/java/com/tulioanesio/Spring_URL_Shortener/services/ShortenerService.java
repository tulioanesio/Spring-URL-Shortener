package com.tulioanesio.Spring_URL_Shortener.services;

import com.tulioanesio.Spring_URL_Shortener.models.Shortener;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.Key;


import java.util.Optional;

@Service
public class ShortenerService {

    private final DynamoDbTemplate dynamoDbTemplate;

    public ShortenerService(DynamoDbTemplate dynamoDbTemplate) {
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    public Shortener createAndSaveShortUrl(String originalUrl) {

        String shortCode = RandomStringUtils.randomAlphanumeric(6);

        Shortener newShortener = new Shortener(shortCode, originalUrl);

        dynamoDbTemplate.save(newShortener);

        return newShortener;
    }

    public Optional<Shortener> findOriginalUrlByShortCode(String shortCode) {
        Key key = Key.builder().partitionValue(shortCode).build();
        Shortener foundShortener = dynamoDbTemplate.load(key, Shortener.class);

        return Optional.ofNullable(foundShortener);
    }
}
