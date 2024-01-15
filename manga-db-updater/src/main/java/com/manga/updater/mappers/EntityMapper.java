package com.manga.updater.mappers;

import com.manga.client.contract.mangaDtos.MangaDto;
import com.manga.client.contract.authorDtos.AuthorDto;
import com.manga.client.contract.dictionaries.TagDto;
import com.manga.model.Author;
import com.manga.model.Genre;
import com.manga.model.Manga;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class EntityMapper implements ICatalogMappers{
    private final IMapEntities<AuthorDto, Author> getAuthorMapper;
    private final IMapEntities<MangaDto, Manga> getMangaMapper;
    private final IMapEntities<TagDto, Genre> getGenreMapper;

    @Override
    public IMapEntities<MangaDto, Manga> getMangaMapper() {
        return getMangaMapper;
    }

    @Override
    public IMapEntities<AuthorDto, Author> getAuthorMapper() {
        return getAuthorMapper;
    }

    @Override
    public IMapEntities<TagDto, Genre> getGenreMapper() {
        return getGenreMapper;
    }
}
