package com.tousime_alternative.controller;

import com.tousime_alternative.dto.OfferDto;
import com.tousime_alternative.model.Offer;
import com.tousime_alternative.service.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController()
@RequestMapping("/api/v1/offer")
public class OfferController {
    private OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("/add")
    public ResponseEntity<Offer> add(@RequestBody OfferDto dto) {
        return new ResponseEntity(offerService.createOffer(dto), CREATED);
    }

    @GetMapping("/all")
    public List<OfferDto> findAll() {
        return offerService.findAll();
    }

    @GetMapping("/partner/{idpartner}")
    public List<OfferDto> findByPartner(@PathVariable("idpartner") Long id) {
        return offerService.findBYPartnerID(id);
    }

    @GetMapping("/recent")
    public List<OfferDto> findRecent() {
        return offerService.findRecent();
    }

    @GetMapping("/mostliked")
    public List<OfferDto> mostliked() {
        return offerService.findMostLiked();
    }

    @PostMapping("/update")
    public ResponseEntity<OfferDto> update(@RequestBody OfferDto user) {
        return ResponseEntity.ok(offerService.update(user));
    }

    @DeleteMapping("/delete/{idOffer}")
    public void delete(@PathVariable("idOffer") Long id) {
        offerService.delete(id);
    }
}
