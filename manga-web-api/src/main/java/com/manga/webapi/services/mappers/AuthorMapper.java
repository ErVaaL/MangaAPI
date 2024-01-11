package com.manga.webapi.services.mappers;

import com.manga.model.Author;
import com.manga.webapi.contract.AuthorDTO;
import org.springframework.stereotype.Component;

@Component
class AuthorEntityMapper implements IMap<AuthorDTO, Author> {
    public Author map(AuthorDTO item) {
        Author author = new Author();
        author.setId(item.getId());
        author.setName(item.getName());
        author.setSourceId(item.getSourceId());
        author.setWebsite(item.getWebsite());
        author.setTwitter(item.getTwitter());
        author.setBiography(item.getBiography());
        return author;
    }
}
@Component
class AuthorDTOMapper implements IMap<Author, AuthorDTO> {
    public AuthorDTO map(Author item) {
        AuthorDTO author = new AuthorDTO();
        author.setId(item.getId());
        author.setName(item.getName());
        author.setSourceId(item.getSourceId());
        author.setWebsite(item.getWebsite());
        author.setTwitter(item.getTwitter());
        author.setBiography(item.getBiography());
        return author;
    }
}
