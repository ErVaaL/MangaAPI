package com.manga.updater.service;

import com.manga.client.IMangaClient;
import com.manga.client.IMangaDictionariesClient;
import com.manga.model.Genre;
import com.manga.model.Manga;
import com.manga.repositories.ICatalogData;
import com.manga.updater.mappers.ICatalogMappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
@Slf4j
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
        var authorsSourceIds = authors.stream().map(author->author.getSourceId()).toList();
        var mangaSourceIds = data.getMangas().findAll().stream().map(Manga::getSourceId).toList();
        var entitiesToSave = mangaEntities.stream().filter(Predicate.not(entity-> mangaSourceIds.contains(entity.getSourceId())))
                .toList();
        entitiesToSave.forEach(entity->{
            var matchingMangaDto = mangaResultList.getData().stream()
                    .filter(mangaDto->mangaDto.getId().equals(entity.getSourceId()))
                    .findFirst().get();
            var mangaRelationships = matchingMangaDto.getRelationships();
            var mangaGenresDtos = matchingMangaDto.getAttributes().getTags().stream().filter(tagDto->tagDto.getAttributes().getGroup().equals("genre")).toList();
            var mangaGenres = mangaGenresDtos.stream().map(tagDto->mapper.getGenreMapper().map(tagDto)).toList();
            var genres = data.getGenres().findAll();
            if(!mangaRelationships.isEmpty()){
                var authorRelationship = mangaRelationships.stream()
                        .filter(relationshipDto->relationshipDto.getType().equals("author"))
                        .findFirst();
                if(authorRelationship.isPresent()){
                    var authorDto = mangaClient.getAuthorById(authorRelationship.get().getId()).getData();
                    var authorEntity = mapper.getAuthorMapper().map(authorDto);
                    if(!authorsSourceIds.contains(authorEntity.getSourceId())) data.getAuthors().save(authorEntity);
                    entity.setAuthor(authorEntity);
                }else {
                    entity.setAuthor(null);
                }
            }
            if(!mangaSourceIds.contains(entity.getSourceId())){
                data.getMangas().save(entity);
            }
            mangaGenresDtos.forEach(tagDto->{
                var genreEntity = data.getGenres().findBySourceId(tagDto.getId());
                if(genreEntity == null){
                    data.getGenres().save(mapper.getGenreMapper().map(tagDto));
                    entity.getGenres().add(data.getGenres().findBySourceId(tagDto.getId()));
                }
                if(!entity.getGenres().contains(genreEntity)){
                    entity.getGenres().add(genreEntity);
                }
            });

        });
    }
    private void updateDictionaries() {
        var tags = dictionariesClient.getTagsDtos();
        Set<Genre> existingGenres = new HashSet<>(data.getGenres().findAll());
        List<Genre> genresFromTags = dictionariesClient.getTagsDtos().stream()
                .filter(tagDto -> tagDto.getAttributes().getGroup().equals("genre"))
                .map(dto->mapper.getGenreMapper().map(dto))
                .filter(Predicate.not(existingGenres::contains))
                .toList();
       data.getGenres().saveAll(genresFromTags);
    }
}
