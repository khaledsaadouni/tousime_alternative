package com.tousime_alternative.service;

import com.tousime_alternative.dto.ReservationDto;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    ReservationDto update(ReservationDto reservationDto);

    Optional<ReservationDto> findById(Long id);

    List<ReservationDto> findAll();

    List<ReservationDto> findAllByPartner(long id);

    List<ReservationDto> findAllByClient(long id);

    void delete(Long id);

    void confirm(Long id);

    void cancel(Long id);

    ReservationDto createReservation(ReservationDto reservationDto, long iduser, long idoffer);
}
