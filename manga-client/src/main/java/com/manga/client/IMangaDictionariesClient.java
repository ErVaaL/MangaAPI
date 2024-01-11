package com.manga.client;

import com.manga.client.contract.dictionaries.TagDto;
import com.manga.client.contract.dictionaries.TagListDto;

import java.util.List;

public interface IMangaDictionariesClient {
    List<TagDto> getTagsDtos();
}
