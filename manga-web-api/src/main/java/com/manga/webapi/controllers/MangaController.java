package com.manga.webapi.controllers;

import com.manga.webapi.contract.AuthorDTO;
import com.manga.webapi.contract.GenreDTO;
import com.manga.webapi.contract.MangaDTO;
import com.manga.webapi.services.IMangaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/mangas")
@RequiredArgsConstructor
public class MangaController {
    private final IMangaService mangaService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveManga(@RequestBody MangaDTO manga){
        long id = mangaService.saveManga(manga);
        return ResponseEntity.ok(id);
    }
    @GetMapping
    public ResponseEntity getMangas(){
        List<MangaDTO> mangas = mangaService.getAllMangas();
        return ResponseEntity.ok(mangas);
    }
    @GetMapping("/{id}")
    public ResponseEntity getMangaById(@PathVariable("id") long id){
        MangaDTO manga = mangaService.getManga(id);
        return ResponseEntity.ok(manga);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteMangaById(@PathVariable("id") long id){
        mangaService.deleteMangaById(id);
        return ResponseEntity.ok(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity updateMangaById(@RequestBody MangaDTO mangaDto, @PathVariable("id")long id){
        long manga = mangaService.updateManga(mangaDto, id);
        return ResponseEntity.ok(manga);
    }
    @GetMapping("/authors")
    public ResponseEntity getAuthors(){
        List<AuthorDTO> authors = mangaService.getAuthors();
        return ResponseEntity.ok(authors);
    }
    @GetMapping("/genres")
    public ResponseEntity getGenres(){
        List<GenreDTO> genres = mangaService.getGenres();
        return ResponseEntity.ok(genres);
    }
}
