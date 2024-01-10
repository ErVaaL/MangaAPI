package com.manga.repositories;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Getter
public class MangaDataCatalog implements ICatalogData{
    private final AuthorRepository authors;
    private final MangaRepository mangas;
    private final GenreRepository genres;
}
