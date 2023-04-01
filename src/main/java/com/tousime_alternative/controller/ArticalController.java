package com.tousime_alternative.controller;

import com.tousime_alternative.dto.ArticalDto;
import com.tousime_alternative.service.ArticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/artical")
public class ArticalController {
    private ArticalService articalService;

    @Autowired
    public ArticalController(ArticalService articalService) {
        this.articalService = articalService;
    }

    @GetMapping("/all/{idShop}")
    public List<ArticalDto> findAll() {
        return articalService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticalDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(articalService.findById(id).orElseThrow());
    }

    @PatchMapping("/update")
    public ResponseEntity<ArticalDto> update(@RequestBody ArticalDto articalDto) {
        return ResponseEntity.ok(articalService.update(articalDto));
    }

    @PostMapping("/add/{idShop}")
    public ResponseEntity<ArticalDto> createArtisan(@RequestBody ArticalDto articalDto, @PathVariable("idShop") long id) {
        return ResponseEntity.ok(articalService.createArtical(articalDto, id));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        articalService.delete(id);
    }
}
