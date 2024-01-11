package com.manga.webapi.services.mappers;

import com.manga.model.Genre;
import com.manga.webapi.contract.GenreDTO;
import com.manga.webapi.contract.TagDTO;
import org.springframework.stereotype.Component;

@Component
class GenreDTOMapper implements IMap<GenreDTO, Genre>{
    @Override
    public Genre map(GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setId(genreDTO.getId());
        genre.setName(genreDTO.getName());
        genre.setSourceId(genreDTO.getSourceId());
        return genre;
    }
}
@Component
class TagDTOMapper implements IMap<TagDTO, Genre>{
    @Override
    public Genre map(TagDTO tagDTO) {
        Genre genre = new Genre();
        genre.setId(tagDTO.getId());
        genre.setName(tagDTO.getName());
        genre.setSourceId(tagDTO.getSourceId());
        return genre;
    }
}
@Component
class GenreEntityMapper implements IMap<Genre, GenreDTO>{
    @Override
    public GenreDTO map(Genre genre) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setName(genre.getName());
        genreDTO.setSourceId(genre.getSourceId());
        return genreDTO;
    }
}
