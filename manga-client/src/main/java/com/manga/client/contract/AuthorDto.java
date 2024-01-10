package com.manga.client.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String id;
    private String name;
    private String biography;
    private String twitter;
    private String website;
    @JsonProperty("relationships")
    private List<MangaSummaryDto> mangas;

}
