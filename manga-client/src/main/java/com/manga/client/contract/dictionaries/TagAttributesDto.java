package com.manga.client.contract.dictionaries;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagAttributesDto {
    private String group;
    private String name;
}
@Getter
class TagAttribute{
    @JsonProperty("en")
    private String value;
}
