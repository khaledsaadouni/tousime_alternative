package com.tousime_alternative.controller;

import com.tousime_alternative.dto.AccomodationDto;
import com.tousime_alternative.dto.OfferDto;
import com.tousime_alternative.service.AccomodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/accomodation")
public class AccomodationController {
    private AccomodationService accomodationService;

    public AccomodationController(AccomodationService accomodationService) {
        this.accomodationService = accomodationService;
    }

    @PostMapping("/add/{idPartner}")
    public ResponseEntity<AccomodationDto> add(@RequestBody AccomodationDto dto, @PathVariable("idPartner") long id) {
        return ResponseEntity.ok(accomodationService.createAccomodation(dto, id));
    }
    @GetMapping("/partner/{idpartner}")
    public List<AccomodationDto> findByPartner(@PathVariable("idpartner") Long id) {
        return accomodationService.findByPartnerId(id);
    }

    @GetMapping("/all")
    public List<AccomodationDto> findAll() {
        return accomodationService.findAll();
    }

    @GetMapping("/{idAccom}")
    public ResponseEntity<AccomodationDto> findById(@PathVariable("idAccom") long id) {
        return ResponseEntity.ok(accomodationService.findById(id));
    }

    @PostMapping("/update")
    public ResponseEntity<AccomodationDto> update(@RequestBody AccomodationDto dto) {
        return ResponseEntity.ok(accomodationService.update(dto));
    }

    @DeleteMapping("/delete/{idAccomodation}")
    public void delete(@PathVariable("idAccomodation") long id) {
        accomodationService.delete(id);
    }
}
