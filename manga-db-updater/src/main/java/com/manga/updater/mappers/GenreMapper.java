package com.manga.updater.mappers;

import com.manga.client.contract.dictionaries.TagDto;
import com.manga.model.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper implements IMapEntities<TagDto, Genre>{
    @Override
    public Genre map(TagDto tagDto) {
        return map(tagDto, new Genre());
    }

    @Override
    public Genre map(TagDto tagDto, Genre genre) {
        genre.setSourceId(tagDto.getId());
        genre.setName(tagDto.getAttributes().getName());
        return genre;
    }
}
