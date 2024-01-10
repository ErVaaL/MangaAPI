package com.manga.repositories;

public interface ICatalogData {
    AuthorRepository getAuthors();
    MangaRepository getMangas();
    GenreRepository getGenres();
}
