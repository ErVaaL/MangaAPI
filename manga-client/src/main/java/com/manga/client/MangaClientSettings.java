package com.manga.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MangaClientSettings implements IMangaClientSettings{
    @Value("${manga.api.url}")
    private String mangaApiUrl;
    @Override
    public String getMangaApiUrl() {
        return mangaApiUrl;
    }
}
