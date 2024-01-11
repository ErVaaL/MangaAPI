package com.manga.webapi.services.mappers;

import com.manga.model.Author;
import com.manga.model.Manga;
import com.manga.webapi.contract.AuthorDTO;
import com.manga.webapi.contract.MangaDTO;
import com.manga.webapi.contract.MangaSummaryDTO;

public interface IMappers {
    IMap<MangaDTO, Manga> getMangaDtoToEntityMapper();
    IMap<Manga, MangaDTO> getMangaToDtoMapper();
    IMap<Manga, MangaSummaryDTO> getMangaToSummaryDtoMapper();
    IMap<Author, AuthorDTO> getAuthorToDtoMapper();
    IMap<AuthorDTO, Author> getAuthorDtoToEntityMapper();
}
