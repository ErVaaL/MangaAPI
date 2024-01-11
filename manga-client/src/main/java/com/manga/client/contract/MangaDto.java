package com.manga.client.contract;

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
    private String title;
    private String description;
    private String originalLanguage;
    private String status;
    private int year;
    private boolean isLocked;
    private List<RelationshipDto> relationships;
}
