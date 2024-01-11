package com.manga.updater.mappers;

import com.manga.client.contract.MangaDto;
import com.manga.model.Manga;
import org.springframework.stereotype.Component;

@Component
public class MangaMapper implements IMapEntities<MangaDto, Manga>{
    @Override
    public Manga map(MangaDto mangaDto) {
        return map(mangaDto, new Manga());
    }

    @Override
    public Manga map(MangaDto mangaDto, Manga manga) {
        manga.setSourceId(mangaDto.getId());
        manga.setOriginalLanguage(mangaDto.getOriginalLanguage());
        manga.setTitle(mangaDto.getTitle());
        manga.setYear(mangaDto.getYear());
        manga.setLocked(mangaDto.isLocked());
        manga.setDescription(mangaDto.getDescription());
        manga.setStatus(mangaDto.getStatus());
    return manga;
    }
}
