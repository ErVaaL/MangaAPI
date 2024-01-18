package com.manga.updater.service;

import com.manga.client.IMangaClient;
import com.manga.client.IMangaDictionariesClient;
import com.manga.client.contract.RelationshipDto;
import com.manga.model.Genre;
import com.manga.repositories.ICatalogData;
import com.manga.updater.mappers.ICatalogMappers;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    @Transactional
    public void updateByYear(int year) {
        if(data.getGenres().findAll().isEmpty()){
            updateDictionaries();
        }
        var mangaResultList = mangaClient.getMangaListByYear(year);
        var mangaEntitiesToSave = mangaResultList.getData().stream().map(dto->mapper.getMangaMapper().map(dto)).toList();
        data.getMangas().saveAll(mangaEntitiesToSave.stream()
                .filter(Predicate.not(entity->data.getMangas().findBySourceId(entity.getSourceId()).isPresent()))
                .toList());

        mangaEntitiesToSave.forEach(entity->{
            var matchingMangaDto = mangaResultList.getData().stream()
                    .filter(mangaDto->mangaDto.getId().equals(entity.getSourceId()))
                    .findFirst().get();
            var mangaGenresDtos = matchingMangaDto.getAttributes().getTags().stream().filter(tagDto->tagDto.getAttributes().getGroup().equals("genre")).toList();

            var relationshipDto = (RelationshipDto) matchingMangaDto.getRelationships().stream()
                    .filter(dto->dto.getType().equals("author"))
                    .findFirst().get();
            var authorEntity = data.getAuthors().findBySourceId(relationshipDto.getId());
            if(authorEntity.isPresent()){
                entity.setAuthor(authorEntity.get());
            }else {
                var authorDto = mangaClient.getAuthorById(relationshipDto.getId()).getData();
                var author = mapper.getAuthorMapper().map(authorDto);
                data.getAuthors().save(author);
                entity.setAuthor(author);
            }

            mangaGenresDtos.forEach(tagDto->{
                var genreEntity = data.getGenres().findBySourceId(tagDto.getId());
                genreEntity.ifPresent(genre -> {
                    if(!entity.getGenres().contains(genre)){
                        entity.getGenres().add(genre);
                    }
                });
                if(genreEntity.isEmpty()){
                    data.getGenres().save(mapper.getGenreMapper().map(tagDto));
                    entity.getGenres().add(mapper.getGenreMapper().map(tagDto));
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
