package com.tousime_alternative.controller;

import com.tousime_alternative.dto.ArtisanDto;
import com.tousime_alternative.service.ArtisanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController()
@RequestMapping("/api/artisan")
public class ArtisanController {
    private ArtisanService artisanService;

    @Autowired
    public ArtisanController(ArtisanService artisanService) {

        this.artisanService = artisanService;
    }

    @GetMapping("/all")
    public List<ArtisanDto> findAll() {
        return artisanService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtisanDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(artisanService.findById(id).orElseThrow());
    }

    @PatchMapping("/update")
    public ResponseEntity<ArtisanDto> update(@RequestBody ArtisanDto artisanDto) {
        return ResponseEntity.ok(artisanService.update(artisanDto));
    }

    @PostMapping("/add/{idPartner}")
    public ResponseEntity<ArtisanDto> createArtisan(@RequestBody ArtisanDto artisanDto, @PathVariable("idPartner") long id) {
        return ResponseEntity.ok(artisanService.createArtisan(artisanDto, id));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        artisanService.delete(id);
    }
}
