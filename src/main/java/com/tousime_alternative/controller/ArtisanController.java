package com.tousime_alternative.controller;

import com.tousime_alternative.dto.ArtisanDto;
import com.tousime_alternative.service.ArtisanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/findById/{id}")
    public Optional<ArtisanDto> findById(@PathVariable("id") Long id) {
        return artisanService.findById(id);
    }
    @PatchMapping("/update")
    public ResponseEntity<ArtisanDto> update(@RequestBody ArtisanDto reservationDto) {
        return ResponseEntity.ok(artisanService.update(reservationDto));
    }
    @PostMapping("/create/{artisan}")
    public ArtisanDto createArtisan(@PathVariable("artisan") ArtisanDto artisanDto) {
        return artisanService.createArtisan(artisanDto);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        artisanService.delete(id);
    }
}
