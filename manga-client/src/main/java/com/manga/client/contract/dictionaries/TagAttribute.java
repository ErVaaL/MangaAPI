package com.manga.client.contract.dictionaries;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TagAttribute{
    @JsonProperty("en")
    private String value;
}

