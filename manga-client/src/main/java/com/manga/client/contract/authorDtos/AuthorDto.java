package com.manga.client.contract.authorDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manga.client.contract.RelationshipDto;
import com.manga.client.contract.mangaDtos.MangaSummaryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {
    @JsonProperty("id")
    private String id;
    @JsonProperty("attributes")
    private AuthorAttributesDto attributes;
    @JsonProperty("relationships")
    private List<RelationshipDto> mangas;

}
