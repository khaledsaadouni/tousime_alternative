package com.tousime_alternative.service;

import com.tousime_alternative.dto.ReservationDto;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    ReservationDto update(ReservationDto reservationDto);

    Optional<ReservationDto> findById(Long id);

    List<ReservationDto> findAll();

    void delete(Long id);

    ReservationDto createReservation(ReservationDto reservationDto, long iduser, long idoffer);
}
