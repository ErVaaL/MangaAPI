package com.manga.client.contract.mangaDtos;

import lombok.Getter;

import java.util.List;

@Getter
public class MangaListResultDto {
    private List<MangaDto> data;
}
