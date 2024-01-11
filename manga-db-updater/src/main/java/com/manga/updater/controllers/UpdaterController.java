package com.manga.updater.controllers;

import com.manga.repositories.ICatalogData;
import com.manga.updater.service.IUpdateManga;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("updater")
public class UpdaterController {
    private final IUpdateManga mangaUpdater;

    @GetMapping("start")
    public ResponseEntity start(@RequestParam int year){
        mangaUpdater.updateByYear(year);
        return ResponseEntity.ok().build();
    }
}
