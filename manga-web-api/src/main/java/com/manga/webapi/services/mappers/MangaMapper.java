package com.manga.webapi.services.mappers;


import com.manga.model.Manga;
import com.manga.webapi.contract.MangaDTO;
import com.manga.webapi.contract.MangaSummaryDTO;
import org.springframework.stereotype.Component;

@Component
class MangaDTOMapper implements IMap<MangaDTO, Manga> {

    @Override
    public Manga map(MangaDTO mangaDTO) {
        Manga manga = new Manga();
        manga.setId(mangaDTO.getId());
        manga.setTitle(mangaDTO.getTitle());
        manga.setSourceId(mangaDTO.getSourceId());
        manga.setLocked(mangaDTO.isLocked());
        manga.setStatus(mangaDTO.getStatus());
        manga.setDescription(mangaDTO.getDescription());
        manga.setYear(mangaDTO.getYear());
        manga.setOriginalLanguage(mangaDTO.getOriginalLanguage());
        return manga;
    }
}
@Component
class MangaEntityMapper implements IMap<Manga, MangaDTO> {

    @Override
    public MangaDTO map(Manga manga) {
        MangaDTO mangaDTO = new MangaDTO();
        mangaDTO.setId(manga.getId());
        mangaDTO.setTitle(manga.getTitle());
        mangaDTO.setSourceId(manga.getSourceId());
        mangaDTO.setStatus(manga.getStatus());
        mangaDTO.setLocked(manga.isLocked());
        mangaDTO.setOriginalLanguage(manga.getOriginalLanguage());
        mangaDTO.setDescription(manga.getDescription());
        mangaDTO.setYear(manga.getYear());
        return mangaDTO;
    }
}
@Component
class MangaSummaryDTOMapper implements IMap<Manga, MangaSummaryDTO> {

    @Override
    public MangaSummaryDTO map(Manga manga) {
        MangaSummaryDTO mangaDTO = new MangaSummaryDTO();
        mangaDTO.setId(manga.getId());
        mangaDTO.setTitle(manga.getTitle());
        mangaDTO.setSourceId(manga.getSourceId());
        return mangaDTO;
    }
}
