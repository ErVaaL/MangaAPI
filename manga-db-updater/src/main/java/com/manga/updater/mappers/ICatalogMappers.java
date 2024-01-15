package com.manga.updater.mappers;

import com.manga.client.contract.authorDtos.AuthorDto;
import com.manga.client.contract.mangaDtos.MangaDto;
import com.manga.client.contract.dictionaries.TagDto;
import com.manga.model.Author;
import com.manga.model.Genre;
import com.manga.model.Manga;

public interface ICatalogMappers {
    IMapEntities<MangaDto, Manga> getMangaMapper();
    IMapEntities<AuthorDto, Author> getAuthorMapper();
    IMapEntities<TagDto, Genre> getGenreMapper();
}

