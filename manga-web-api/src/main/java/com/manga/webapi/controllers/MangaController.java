package com.manga.webapi.controllers;

import com.manga.webapi.contract.MangaDTO;
import com.manga.webapi.services.IMangaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/mangas")
@RequiredArgsConstructor
public class MangaController {
    private final IMangaService mangaService;

    @PostMapping
    public ResponseEntity saveManga(@RequestBody MangaDTO manga){
        var id = mangaService.saveManga(manga);
        return ResponseEntity.ok(id);
    }
    @GetMapping
    public ResponseEntity getMangas(){
        var mangas = mangaService.getAllMangas();
        return ResponseEntity.ok(mangas);
    }
    @GetMapping("/{id}")
    public ResponseEntity getMangaById(@PathVariable("id") long id){
        var manga = mangaService.getManga(id);
        return ResponseEntity.ok(manga);
    }
    @DeleteMapping
    public ResponseEntity deleteMangaById(@PathVariable("id") long id){
        mangaService.deleteMangaById(id);
        return ResponseEntity.ok(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity updateMangaById(@RequestBody MangaDTO mangaDto, @PathVariable("id")long id){
        var manga = mangaService.updateManga(mangaDto, id);
        return ResponseEntity.ok(manga);
    }
    @GetMapping("/authors")
    public ResponseEntity getAuthors(){
        var authors = mangaService.getAuthors();
        return ResponseEntity.ok(authors);
    }
    @GetMapping("/genres")
    public ResponseEntity getGenres(){
        var genres = mangaService.getGenres();
        return ResponseEntity.ok(genres);
    }
}
