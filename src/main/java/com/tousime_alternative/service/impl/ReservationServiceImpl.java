package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.ReservationDto;
import com.tousime_alternative.model.Reservation;
import com.tousime_alternative.repository.OfferRepository;
import com.tousime_alternative.repository.ReservationRepository;
import com.tousime_alternative.repository.UserRepository;
import com.tousime_alternative.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository reservationRepository;
    private UserRepository userRepository;
    private OfferRepository offerRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, UserRepository userRepository, OfferRepository offerRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
    }

    @Override
    public ReservationDto update(ReservationDto reservationDto) {
        Reservation reservation = ReservationDto.toEntity(reservationDto);
        reservation.setLastModifiedDate(Instant.now());
        return ReservationDto.fromEntity(reservationRepository.save(reservation));
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
    public ReservationDto createReservation(ReservationDto reservationDto, long iduser, long idoffer) {
        Reservation reservation = ReservationDto.toEntity(reservationDto);
        reservation.setCreationDate(Instant.now());
        reservation.setUser(userRepository.findById(iduser).orElseThrow());
        reservation.setOffer(offerRepository.findById(idoffer).orElseThrow());
        return ReservationDto.fromEntity(reservationRepository.save(reservation));
    }
}
