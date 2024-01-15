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
        var mangaResultList = mangaClient.getMangaListByYear(year);
        var mangaEntities = mangaResultList.getData().stream().map(dto->mapper.getMangaMapper().map(dto)).toList();

        var authors = data.getAuthors().findAll();
        var mangaSourceIds = data.getMangas().findAll().stream().map(Manga::getSourceId).toList();
        var entitiesToSave = mangaEntities.stream().filter(Predicate.not(entity-> mangaSourceIds.contains(entity.getSourceId())))
                .toList();
        entitiesToSave.forEach(entity->{
            var matchingMangaDto = mangaResultList.getData().stream()
                    .filter(mangaDto->mangaDto.getId().equals(entity.getSourceId()))
                    .findFirst().get();
            var mangaRelationships = matchingMangaDto.getRelationships();
            if(!mangaRelationships.isEmpty()){
                var authorRelationship = mangaRelationships.stream()
                        .filter(relationshipDto->relationshipDto.getType().equals("author"))
                        .findFirst().get();
                var authorDto = mangaClient.getAuthorById(authorRelationship.getId()).getData();
                var authorEntity = mapper.getAuthorMapper().map(authorDto);
                if(!authors.contains(authorEntity)) data.getAuthors().save(authorEntity);
                entity.setAuthor(authorEntity);
            }
            data.getMangas().save(entity);
        });

    }
    private void updateDictionaries() {
        var tags = dictionariesClient.getTagsDtos();
        var genres = data.getGenres().findAll();
        var genresFromTags = dictionariesClient.getTagsDtos().stream()
                .filter(tagDto -> tagDto.getAttributes().getGroup().equals("genre"))
                .map(dto->mapper.getGenreMapper().map(dto))
                .toList();
        genresFromTags.stream()
                .filter(Predicate.not(genresFromTags::contains))
                .forEach(genre->data.getGenres().save(genre));
    }
}
