package com.manga.updater.mappers;

import com.manga.client.contract.AuthorDto;
import com.manga.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements IMapEntities<AuthorDto, Author>{
    @Override
    public Author map(AuthorDto authorDto) {
        return map(authorDto, new Author());
    }

    @Override
    public Author map(AuthorDto authorDto, Author author) {
        author.setSourceId(authorDto.getId());
        author.setBiography(authorDto.getBiography());
        author.setTwitter(authorDto.getTwitter());
        author.setWebsite(authorDto.getWebsite());
        author.setName(authorDto.getName());
        return author;
    }
}
