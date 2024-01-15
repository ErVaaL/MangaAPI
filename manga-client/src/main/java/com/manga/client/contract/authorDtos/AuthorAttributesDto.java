package com.manga.client.contract.authorDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorAttributesDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("biography")
    private BiographyDto biography;
    @JsonProperty("twitter")
    private String twitter;
    @JsonProperty("website")
    private String website;
}
