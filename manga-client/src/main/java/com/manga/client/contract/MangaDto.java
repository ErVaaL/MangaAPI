package com.manga.client.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MangaDto extends MangaSummaryDto{
    @JsonProperty("attributes")
    private MangaAttributesDto attributes;
    private List<RelationshipDto> relationships;
}
