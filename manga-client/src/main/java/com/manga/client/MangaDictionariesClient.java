package com.manga.client;

import com.manga.client.contract.dictionaries.TagDto;
import com.manga.client.contract.dictionaries.TagListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MangaDictionariesClient implements IMangaDictionariesClient{
    RestTemplate restClient = new RestTemplate();

    private final IMangaClientSettings settings;

    @Override
    public List<TagDto> getTagsDtos() {
        String url = settings.getUrlBuilder()
                .pathSegment("manga", "tag")
                .build()
                .toUriString();
        return restClient.getForObject(url, TagListDto.class).getData();
    }
}
