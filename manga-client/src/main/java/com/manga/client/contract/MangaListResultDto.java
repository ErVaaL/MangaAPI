package com.manga.client.contract;

import lombok.Getter;

import java.util.List;

@Getter
public class MangaListResultDto {
    private List<MangaDto> data;
}
