package com.tousime_alternative.controller;

import com.tousime_alternative.dto.RestaurationDto;
import com.tousime_alternative.model.Restauration;
import com.tousime_alternative.service.RestaurationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController()
@RequestMapping("/api/v1/restauration")
public class RestaurationController {
    private RestaurationService restaurationService;
    @PostMapping("/add")
    public ResponseEntity<Restauration> add(@RequestBody RestaurationDto dto){
        return new ResponseEntity(restaurationService.createRestauration(dto), CREATED);
    }
    public RestaurationController(RestaurationService restaurationService) {
        this.restaurationService = restaurationService;
    }
    @GetMapping("/all")
    public List<RestaurationDto> findAll() {
        return restaurationService.findAll();
    }

    @PostMapping("/update")
    public ResponseEntity<RestaurationDto> update(@RequestBody RestaurationDto dto) {
        return ResponseEntity.ok(restaurationService.update(dto));
    }

    @DeleteMapping("/delete/{idrestauration}")
    public void delete(@PathVariable("idrestauration") Long id) {
        restaurationService.delete(id);
    }
}
