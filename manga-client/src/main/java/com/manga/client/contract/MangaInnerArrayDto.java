package com.manga.client.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MangaInnerArrayDto {
    @JsonProperty("en")
    private String value;
}
