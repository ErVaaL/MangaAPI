package com.manga.webapi.services;

import com.manga.model.Genre;
import com.manga.repositories.ICatalogData;
import com.manga.webapi.contract.AuthorDTO;
import com.manga.webapi.contract.GenreDTO;
import com.manga.webapi.contract.MangaDTO;
import com.manga.webapi.services.mappers.Mappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MangaService implements IMangaService{
    private final ICatalogData db;
    private final Mappers mappers;
    @Override
    public MangaDTO getManga(long id) {
        var mangaEntity = db.getMangas().findById(id).orElse(null);
        if(mangaEntity == null) return null;
        return mappers.getMangaToDtoMapper().map(mangaEntity);
    }

    @Override
    public void deleteMangaById(long id) {
        db.getMangas().deleteById(id);
    }

    @Override
    public long updateManga(MangaDTO mangaDto, long id) {
        mangaDto.setId(id);
        db.getMangas().save(mappers.getMangaDtoToEntityMapper().map(mangaDto));
        return id;
    }

    @Override
    public List<GenreDTO> getGenres() {
        var genres = db.getGenres().findAll();
        return genres.stream().map(this::mapGenreDto).toList();
    }

    @Override
    public long saveManga(MangaDTO dto) {
        var mangaEntity = mappers.getMangaDtoToEntityMapper().map(dto);
        db.getMangas().save(mangaEntity);
        return mangaEntity.getId();
    }

    @Override
    public long saveAuthor(AuthorDTO authorDto, long id) {
        var AuthorEntity = mappers.getGetAuthorDtoToEntityMapper().map(authorDto);
        db.getAuthors().save(AuthorEntity);
        return AuthorEntity.getId();
    }

    @Override
    public void deleteAuthorById(long id) {
        db.getAuthors().deleteById(id);
    }

    @Override
    public long updateAuthor(AuthorDTO authorDto, long id) {
        authorDto.setId(id);
        db.getAuthors().save(mappers.getGetAuthorDtoToEntityMapper().map(authorDto));
        return id;
    }

    @Override
    public AuthorDTO getAuthor(long id) {
        var authorEntity = db.getAuthors().findById(id).orElse(null);
        if(authorEntity == null) return null;
        return mappers.getGetAuthorToDtoMapper().map(authorEntity);
    }

    private GenreDTO mapGenreDto(Genre genre){
        var genreDto = new GenreDTO();
        genreDto.setId(genre.getId());
        genreDto.setName(genre.getName());
        genreDto.setSourceId(genre.getSourceId());
        return genreDto;
    }
}
