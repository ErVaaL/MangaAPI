package com.manga.client.contract.mangaDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MangaAttributesDto {
    private MangaInnerArrayDto title;
    private MangaInnerArrayDto description;
    private String originalLanguage;
    private String status;
    private int year;
    private boolean isLocked;
}
