package com.manga.client;

import com.manga.client.contract.authorDtos.AuthorDto;
import com.manga.client.contract.authorDtos.AuthorResultDto;
import com.manga.client.contract.mangaDtos.MangaDto;
import com.manga.client.contract.mangaDtos.MangaListResultDto;

public interface IMangaClient {
    MangaListResultDto getMangaListByYear(int year);
    MangaDto getMangaById(String id);
    AuthorResultDto getAuthorById(String id);
}
