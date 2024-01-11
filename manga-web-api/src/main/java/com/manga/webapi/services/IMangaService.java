package com.manga.webapi.services;

import com.manga.webapi.contract.AuthorDTO;
import com.manga.webapi.contract.GenreDTO;
import com.manga.webapi.contract.MangaDTO;

import java.util.List;

public interface IMangaService {
    MangaDTO getManga(long id);
    void deleteMangaById(long id);
    long updateManga(MangaDTO mangaDto, long id);
    List<GenreDTO> getGenres();
    long saveManga(MangaDTO dto);
    long saveAuthor(AuthorDTO authorDto, long id);
    void deleteAuthorById(long id);
    long updateAuthor(AuthorDTO authorDto, long id);
    AuthorDTO getAuthor(long id);
    List<MangaDTO> getAllMangas();

    List<AuthorDTO> getAuthors();
}
