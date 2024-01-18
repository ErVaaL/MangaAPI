package com.manga.repositories;

import com.manga.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findBySourceId(String sourceId);
    @Query("select g from Genre g")
    List<Genre> findAll();
}
