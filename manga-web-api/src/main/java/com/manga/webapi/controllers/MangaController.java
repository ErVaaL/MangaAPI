package com.manga.webapi.controllers;

import com.manga.webapi.services.IMangaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/mangas")
@RequiredArgsConstructor
public class MangaController {
    private final IMangaService mangaService;

}
