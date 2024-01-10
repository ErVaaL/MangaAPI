package com.manga.webapi.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TagDTO {
    private long id;
    private String name;
    private String group;
}
