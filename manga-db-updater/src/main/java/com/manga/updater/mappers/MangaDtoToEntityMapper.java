package com.manga.updater.mappers;

import com.manga.client.contract.mangaDtos.MangaDto;
import com.manga.model.Manga;
import org.springframework.stereotype.Component;

@Component
public class MangaDtoToEntityMapper implements IMapEntities<MangaDto, Manga>{
    @Override
    public Manga map(MangaDto mangaDto) {
        return map(mangaDto, new Manga());
    }

    @Override
    public Manga map(MangaDto mangaDto, Manga manga) {
        manga.setSourceId(mangaDto.getId());
        manga.setOriginalLanguage(mangaDto.getAttributes().getOriginalLanguage());
        manga.setTitle(mangaDto.getAttributes().getTitle().getValue());
        manga.setYear(mangaDto.getAttributes().getYear());
        manga.setLocked(mangaDto.getAttributes().isLocked());
        manga.setDescription(mangaDto.getAttributes().getDescription().getValue());
        manga.setStatus(mangaDto.getAttributes().getStatus());
    return manga;
    }
}
