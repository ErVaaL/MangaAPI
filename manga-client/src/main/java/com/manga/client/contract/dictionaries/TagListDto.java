package com.manga.client.contract.dictionaries;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TagListDto {
    public List<TagDto> tags;
}
