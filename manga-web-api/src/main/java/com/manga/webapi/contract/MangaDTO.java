package com.manga.webapi.contract;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MangaDTO extends MangaSummaryDTO{
    private String status;
    private boolean isLocked;
    private int year;
}
