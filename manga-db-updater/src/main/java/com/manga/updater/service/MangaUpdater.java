package com.manga.updater.service;

import com.manga.client.IMangaClient;
import com.manga.client.IMangaDictionariesClient;
import com.manga.model.Manga;
import com.manga.repositories.ICatalogData;
import com.manga.updater.mappers.ICatalogMappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class MangaUpdater implements IUpdateManga{
    private final ICatalogData data;
    private final IMangaClient mangaClient;
    private final IMangaDictionariesClient dictionariesClient;
    private final ICatalogMappers mapper;
    @Override
    public void updateByYear(int year) {
        updateDictionaries();
        var MangaResultList = mangaClient.getMangaListByYear(year);
        var mangas = MangaResultList.getData().stream().map(dto->mangaClient.getMangaById(dto.getId())).toList();
        var mangaEntities = mangas.stream().map(dto->mapper.getMangaMapper().map(dto)).toList();

        var authors = data.getAuthors().findAll();

        var mangaSourceIds = data.getMangas().findAll().stream().map(Manga::getSourceId).toList();
        var entitiesToSave = mangaEntities.stream().filter(Predicate.not(entity-> mangaSourceIds.contains(entity.getSourceId())))
                .toList();
        entitiesToSave.forEach(entity->{
            data.getMangas().save(entity);
            var author = mangas.stream()
                    .filter(mangaDto->mangaDto.getId().equals(entity.getSourceId()))
                    .findFirst().get()
                    .getRelationships()
                    .stream().filter(relationshipDto->relationshipDto.getType().equals("author"))
                    .map(relationshipDto->mangaClient.getAuthorById(relationshipDto.getId())).map(dto->mapper.getAuthorMapper().map(dto)).findFirst().get();
            if(!authors.contains(author)) data.getAuthors().save(author);
            entity.setAuthor(author);
        });

    }
    private void updateDictionaries() {
        var genres = data.getGenres().findAll();
        var genresFromTags = dictionariesClient.getTagsDtos().stream()
                .filter(tagDto -> tagDto.getGroup().equals("genre"))
                .map(dto->mapper.getGenreMapper().map(dto))
                .toList();
        genresFromTags.stream()
                .filter(Predicate.not(genre->genres.contains(genre)))
                .forEach(genre->data.getGenres().save(genre));
    }
}
