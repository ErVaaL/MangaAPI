package com.manga.updater.service;

import com.manga.client.IMangaClient;
import com.manga.client.IMangaDictionariesClient;
import com.manga.client.contract.RelationshipDto;
import com.manga.client.contract.authorDtos.AuthorDto;
import com.manga.client.contract.dictionaries.TagDto;
import com.manga.client.contract.mangaDtos.MangaDto;
import com.manga.client.contract.mangaDtos.MangaListResultDto;
import com.manga.model.Author;
import com.manga.model.Genre;
import com.manga.model.Manga;
import com.manga.repositories.ICatalogData;
import com.manga.updater.mappers.ICatalogMappers;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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

        if(data.getGenres().findAll().isEmpty()) updateDictionaries();

        MangaListResultDto mangaResultList = mangaClient.getMangaListByYear(year);

        List<Manga> mangaEntitiesToSave = mangaResultList.getData()
                .stream()
                .map(dto->mapper.getMangaMapper().map(dto)).toList();

        saveMangaEntities(mangaEntitiesToSave);

        mangaEntitiesToSave.forEach(entity->{
            MangaDto matchingMangaDto = mangaResultList.getData().stream()
                    .filter(mangaDto->mangaDto.getId().equals(entity.getSourceId()))
                    .findFirst().get();

            saveAuthorAndRelation(entity, matchingMangaDto);
            saveMangaGenreRelation(entity, matchingMangaDto);
        });
    }

    private void saveMangaGenreRelation(Manga entity, MangaDto matchingMangaDto) {
        List<TagDto> mangaGenresDtos = matchingMangaDto.getAttributes()
                .getTags()
                .stream()
                .filter(tagDto->tagDto.getAttributes()
                        .getGroup()
                        .equals("genre"))
                .toList();

        mangaGenresDtos.forEach(tagDto->{
            Optional<Genre> genreEntity = data.getGenres().findBySourceId(tagDto.getId());
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
    }

    private void saveAuthorAndRelation(Manga entity, MangaDto matchingMangaDto) {
        RelationshipDto relationshipDto = matchingMangaDto.getRelationships()
                .stream()
                .filter(dto->dto.getType().equals("author"))
                .findFirst().get();
        Optional<Author> authorEntity = data.getAuthors().findBySourceId(relationshipDto.getId());
        if(authorEntity.isPresent()){
            entity.setAuthor(authorEntity.get());
        }else {
            AuthorDto authorDto = mangaClient.getAuthorById(relationshipDto.getId()).getData();
            Author author = mapper.getAuthorMapper().map(authorDto);
            data.getAuthors().save(author);
            entity.setAuthor(author);
        }
    }

    private void saveMangaEntities(List<Manga> mangaEntitiesToSave) {
        data.getMangas().saveAll(mangaEntitiesToSave.stream()
                .filter(Predicate.not(entity->data.getMangas().findBySourceId(entity.getSourceId()).isPresent()))
                .toList());
    }

    private void updateDictionaries() {
        List<TagDto> tags = dictionariesClient.getTagsDtos();
        Set<Genre> existingGenres = new HashSet<>(data.getGenres().findAll());
        List<Genre> genresFromTags = dictionariesClient.getTagsDtos().stream()
                .filter(tagDto -> tagDto.getAttributes().getGroup().equals("genre"))
                .map(dto->mapper.getGenreMapper().map(dto))
                .filter(Predicate.not(existingGenres::contains))
                .toList();
       data.getGenres().saveAll(genresFromTags);
    }
}
