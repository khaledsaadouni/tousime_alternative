package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.ArtisanDto;
import com.tousime_alternative.dto.ReservationDto;
import com.tousime_alternative.dto.UserDto;
import com.tousime_alternative.model.Reservation;
import com.tousime_alternative.model.User;
import com.tousime_alternative.repository.ReservationRepository;
import com.tousime_alternative.repository.UserRepository;
import com.tousime_alternative.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;

    }
    @Override
    public ReservationDto update(ReservationDto reservationDto) {
        Reservation reservation= reservationRepository.findById(reservationDto.getId()).orElseThrow();
        reservation.setDate(reservationDto.getDate());
        reservation.setState(reservationDto.getState());
        reservation.setNbre_personne(reservation.getNbre_personne());
        reservation.setLastModifiedDate(Instant.now());
        var reserv = reservationRepository.save(reservation);
        return ReservationDto.fromEntity(reserv);
    }

    @Override
    public Optional<ReservationDto> findById(Long id) {
        return reservationRepository.findById(id)
                .map(ReservationDto::fromEntity)
                .stream().findFirst();
    }

    @Override
    public List<ReservationDto> findAll() {
        return reservationRepository.findAll().stream()
                .map(ReservationDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public ReservationDto createReservation(ReservationDto reservationDto) {
        return ReservationDto.fromEntity(reservationRepository.save(ReservationDto.toEntity(reservationDto)));
    }
}
