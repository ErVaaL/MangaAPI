package com.manga.client;

import com.manga.client.contract.dictionaries.TagDto;

import java.util.List;

public interface IMangaDictionariesClient {
    List<TagDto> getTags();
}
