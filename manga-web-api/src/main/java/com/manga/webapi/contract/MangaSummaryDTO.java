package com.manga.webapi.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MangaSummaryDTO {
    private long id;
    private String title;
    private String description;
    private String originalLanguage;
}
