package com.manga.client;

import com.manga.client.contract.AuthorDto;
import com.manga.client.contract.MangaDto;
import com.manga.client.contract.MangaListResultDto;

public interface IMangaClient {
    MangaListResultDto getMangaListByYear(int year);
    MangaDto getMangaById(String id);
    AuthorDto getAuthorById(String id);
}
