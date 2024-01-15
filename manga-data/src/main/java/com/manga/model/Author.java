package com.manga.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String biography;
    private String twitter;
    private String website;
    @Column(unique = true)
    private String sourceId;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Manga> mangas = new ArrayList<>();
}
