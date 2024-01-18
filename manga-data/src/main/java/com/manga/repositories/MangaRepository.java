package com.manga.repositories;

import com.manga.model.Manga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MangaRepository extends JpaRepository<Manga, Long> {
    Optional<Manga> findBySourceId(String sourceId);
}
