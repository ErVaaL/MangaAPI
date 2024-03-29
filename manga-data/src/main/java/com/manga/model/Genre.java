package com.manga.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String sourceId;
    private String name;
    @ManyToMany(mappedBy = "genres")
    private List<Manga> mangas = new ArrayList<>();
}
