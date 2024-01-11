package com.manga.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sourceId;
    private String title;
    private String description;
    private String originalLanguage;
    private String status;
    private int year;
    private boolean isLocked;
    @ManyToOne
    @JoinColumn(name = "author_id",referencedColumnName = "id")
    private Author author;
    @ManyToMany
    @JoinTable(
            name = "manga_genre",
            joinColumns = @JoinColumn(name = "manga_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres = new ArrayList<>();

}
