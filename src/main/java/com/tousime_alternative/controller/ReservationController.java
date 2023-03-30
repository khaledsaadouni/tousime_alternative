package com.tousime_alternative.controller;

import com.tousime_alternative.dto.ReservationDto;
import com.tousime_alternative.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController()
@RequestMapping("/api/v1/reservation")
public class ReservationController {
    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {

        this.reservationService = reservationService;
    }

    @GetMapping("/all")
    public List<ReservationDto> findAll() {
        return reservationService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Optional<ReservationDto> findById(@PathVariable("id") Long id) {
        return reservationService.findById(id);
    }
    @PatchMapping("/update")
    public ResponseEntity<ReservationDto> update(@RequestBody ReservationDto reservationDto) {
        return ResponseEntity.ok(reservationService.update(reservationDto));
    }
    @PostMapping("/create/{reservation}")
    public ReservationDto createReservation(@PathVariable("reservation") ReservationDto reservationDto) {
        return reservationService.createReservation(reservationDto);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        reservationService.delete(id);
    }

}
