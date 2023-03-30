package com.tousime_alternative.controller;

import com.tousime_alternative.dto.AccomodationDto;
import com.tousime_alternative.model.Accomodation;
import com.tousime_alternative.service.AccomodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController()
@RequestMapping("/api/v1/accomodation")
public class AccomodationController {
    private AccomodationService accomodationService;
    @PostMapping("/add")
    public ResponseEntity<Accomodation> add(@RequestBody AccomodationDto dto){
        return new ResponseEntity(accomodationService.createAccomodation(dto), CREATED);
    }
    public AccomodationController(AccomodationService accomodationService) {
        this.accomodationService = accomodationService;
    }
    @GetMapping("/all")
    public List<AccomodationDto> findAll() {
        return accomodationService.findAll();
    }

    @PostMapping("/update")
    public ResponseEntity<AccomodationDto> update(@RequestBody AccomodationDto dto) {
        return ResponseEntity.ok(accomodationService.update(dto));
    }

    @DeleteMapping("/delete/{idAccomodation}")
    public void delete(@PathVariable("idAccomodation") Long id) {
        accomodationService.delete(id);
    }
}
