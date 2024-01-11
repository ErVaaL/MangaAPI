package com.manga.webapi.services.mappers;

import com.manga.model.Author;
import com.manga.model.Manga;
import com.manga.webapi.contract.AuthorDTO;
import com.manga.webapi.contract.MangaDTO;
import com.manga.webapi.contract.MangaSummaryDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Accessors
public class Mappers implements IMappers {
    private final IMap<MangaDTO, Manga> getMangaDtoToEntityMapper;
    private final IMap<Manga, MangaDTO> getMangaToDtoMapper;
    private final IMap<Manga, MangaSummaryDTO> getMangaToSummaryDtoMapper;
    private final IMap<Author, AuthorDTO> getAuthorToDtoMapper;
    private final IMap<AuthorDTO, Author> getAuthorDtoToEntityMapper;
}

