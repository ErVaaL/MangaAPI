package com.manga.updater.mappers;

import com.manga.client.contract.authorDtos.AuthorDto;
import com.manga.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorDtoToEntityMapper implements IMapEntities<AuthorDto, Author>{
    @Override
    public Author map(AuthorDto authorDto) {
        return map(authorDto, new Author());
    }

    @Override
    public Author map(AuthorDto authorDto, Author author) {
        author.setSourceId(authorDto.getId());
        author.setBiography(authorDto.getAttributes().getBiography().getEn());
        author.setTwitter(authorDto.getAttributes().getTwitter());
        author.setWebsite(authorDto.getAttributes().getWebsite());
        author.setName(authorDto.getAttributes().getName());
        return author;
    }
}
