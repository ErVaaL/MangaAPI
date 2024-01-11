package com.manga.client.contract.dictionaries;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class TagListDto {
    @JsonProperty("data")
    public List<TagDto> data;

    public List<TagDto> getData() {
        return data;
    }
    public void setData(List<TagDto> data) {
        this.data = data;
    }
}
