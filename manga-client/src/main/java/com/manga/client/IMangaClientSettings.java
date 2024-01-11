package com.manga.client;

import org.springframework.web.util.UriComponentsBuilder;

public interface IMangaClientSettings {
    String getMangaApiUrl();
    default UriComponentsBuilder getUrlBuilder(){
        return UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host(getMangaApiUrl());
    }
}
