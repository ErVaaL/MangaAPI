package com.manga.client.contract.mangaDtos;

import com.manga.client.contract.dictionaries.TagDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MangaAttributesDto {
    private List<TagDto> tags;
    private MangaInnerArrayDto title;
    private MangaInnerArrayDto description;
    private String originalLanguage;
    private String status;
    private int year;
    private boolean isLocked;
}
