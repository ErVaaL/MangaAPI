package com.manga.client;

import com.manga.client.contract.AuthorDto;
import com.manga.client.contract.MangaDto;
import com.manga.client.contract.MangaListResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class MangaClient implements IMangaClient{
    private final RestTemplate restClient = new RestTemplate();
    private String baseUrl;
    private final IMangaClientSettings settings;

    @Override
    public MangaListResultDto getMangaListByYear(int year) {
        var url = settings.getUrlBuilder()
                .pathSegment("manga")
                .queryParam("year", year)
                .build()
                .toUriString();
        return restClient.getForObject(url, MangaListResultDto.class);
    }

    @Override
    public MangaDto getMangaById(String id) {
        var url = settings.getUrlBuilder()
                .pathSegment("manga", id)
                .build()
                .toUriString();
        return restClient.getForObject(url, MangaDto.class);
    }

    @Override
    public AuthorDto getAuthorById(String id) {
        var url = settings.getUrlBuilder()
                .pathSegment("author", id)
                .build()
                .toUriString();
        return restClient.getForObject(url, AuthorDto.class);
    }
}
