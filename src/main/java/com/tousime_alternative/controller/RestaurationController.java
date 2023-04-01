package com.tousime_alternative.controller;

import com.tousime_alternative.dto.RestorationDto;
import com.tousime_alternative.service.RestorationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/restoration")
public class RestaurationController {
    private RestorationService restaurationService;

    public RestaurationController(RestorationService restaurationService) {
        this.restaurationService = restaurationService;
    }

    @PostMapping("/add/{idPartner}")
    public ResponseEntity<RestorationDto> add(@RequestBody RestorationDto dto, @PathVariable("idPartner") long id) {
        return ResponseEntity.ok(restaurationService.createRestauration(dto, id));
    }

    @GetMapping("/all")
    public List<RestorationDto> findAll() {
        return restaurationService.findAll();
    }

    @GetMapping("/{idResto}")
    public ResponseEntity<RestorationDto> findById(@PathVariable("idResto") long id) {
        return ResponseEntity.ok(restaurationService.findById(id).orElseThrow());
    }

    @PostMapping("/update")
    public ResponseEntity<RestorationDto> update(@RequestBody RestorationDto dto) {
        return ResponseEntity.ok(restaurationService.update(dto));
    }

    @DeleteMapping("/delete/{idrestauration}")
    public void delete(@PathVariable("idrestauration") Long id) {
        restaurationService.delete(id);
    }
}
