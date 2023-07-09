package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.ExtrasDto;
import com.tousime_alternative.dto.ReservationDto;
import com.tousime_alternative.exception.EntityNotFoundException;
import com.tousime_alternative.model.Accomodation;
import com.tousime_alternative.model.Offer;
import com.tousime_alternative.model.Reservation;
import com.tousime_alternative.model.enumr.State;
import com.tousime_alternative.repository.AccomodationRepository;
import com.tousime_alternative.repository.OfferRepository;
import com.tousime_alternative.repository.ReservationRepository;
import com.tousime_alternative.repository.UserRepository;
import com.tousime_alternative.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository reservationRepository;
    private UserRepository userRepository;
    private AccomodationRepository accomodationRepository;
    private OfferRepository offerRepository;
    private ExtrasServiceImpl extrasService;

    @Autowired
    public ReservationServiceImpl(ExtrasServiceImpl extrasService, AccomodationRepository accomodationRepository, ReservationRepository reservationRepository, UserRepository userRepository, OfferRepository offerRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.extrasService = extrasService;
        this.offerRepository = offerRepository;
        this.accomodationRepository = accomodationRepository;
    }

    public static boolean isIntersecting(Date checkIn1, Date checkOut1,
                                         Date checkIn2, Date checkOut2) {
        return checkIn1.before(checkOut2) && checkIn2.before(checkOut1);
    }

    @Override
    public ReservationDto update(ReservationDto reservationDto) {
        Reservation reservation = reservationRepository.findById(reservationDto.getId()).orElseThrow();
        reservation.setPrice(reservationDto.getPrice());
        reservation.setCheckout(reservationDto.getCheckout());
        reservation.setDate(reservationDto.getDate());
        reservation.setState(reservationDto.getState());
        reservation.setCount_people(reservationDto.getCount_people());
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
    public List<ReservationDto> findAllByPartner(long id) {
        return reservationRepository.findAllByOfferPartnerId(id).stream()
                .map(ReservationDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDto> findAllByClient(long id) {
        return reservationRepository.findAllByUserId(id).stream()
                .map(ReservationDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public void confirm(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        reservation.setState(State.Confirmed);
        reservationRepository.save(reservation);
    }

    @Override
    public void cancel(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        reservation.setState(State.Canceled);
        reservationRepository.save(reservation);
    }

    @Override
    public ReservationDto createReservation(ReservationDto reservationDto, long iduser, long idoffer) {


        Offer offer = offerRepository.findById(idoffer).orElseThrow();
        Reservation r = ReservationDto.toEntity(reservationDto);
        if (offer.getGeneric_Type().equals("accomodation")) {
            Accomodation accomodation = accomodationRepository.findById(offer.getId()).orElseThrow();
            if (accomodation.getAllow_many_reservation()) {
                List<Reservation> reservations = reservationRepository.findAllByOfferId(offer.getId());
                Integer count = 0;
                for (Reservation res : reservations
                ) {
                    if (isIntersecting(reservationDto.getDate(), reservationDto.getCheckout(), res.getDate(), res.getCheckout())) {
                        count += res.getCount_people();
                    }

                }
                if (reservationDto.getCount_people() == 0) {
                    throw new RuntimeException("Please enter a valid number.");
                }
                if (count + reservationDto.getCount_people() > offer.getCapacity()) {
                    throw new RuntimeException((offer.getCapacity() - count) + " places are left for this offer");
                } else {

                    r.setCreationDate(Instant.now());
                    r.setUser(userRepository.findById(iduser).orElseThrow());
                    r.setOffer(offer);
                    r.setCount_people(reservationDto.getCount_people());
                    r.setState(State.Confirmed);
                    Reservation resev = reservationRepository.save(r);
                    for (ExtrasDto extrasDto : reservationDto.getExtras()) {
                        extrasService.createExtrasReservation(extrasDto, resev.getId());
                    }
                    return ReservationDto.fromEntity(resev);

                }

            } else {


                Reservation reservation = reservationRepository.findLastReservationByOfferAndDate(offer, reservationDto.getDate());
                Reservation reservation2 = reservationRepository.findFirstReservationByOfferAndDate(offer, reservationDto.getDate());
                if (reservationDto.getCount_people() > offer.getCapacity()) {
                    throw new RuntimeException("The number of persons is higher than the accommodation capacity");
                } else if (reservation == null && reservation2 == null) {
                    r.setCreationDate(Instant.now());
                    r.setUser(userRepository.findById(iduser).orElseThrow());
                    r.setOffer(offer);
                    r.setCount_people(reservationDto.getCount_people());
                    r.setState(State.Confirmed);
                    Reservation resev = reservationRepository.save(r);
                    for (ExtrasDto extrasDto : reservationDto.getExtras()) {
                        extrasService.createExtrasReservation(extrasDto, resev.getId());
                    }
                    return ReservationDto.fromEntity(resev);
                } else if (reservation != null && reservation2 == null && reservationDto.getDate().getTime() > reservation.getCheckout().getTime()) {
                    r.setCreationDate(Instant.now());
                    r.setUser(userRepository.findById(iduser).orElseThrow());
                    r.setOffer(offer);
                    r.setCount_people(reservationDto.getCount_people());
                    r.setState(State.Confirmed);
                    Reservation resev = reservationRepository.save(r);
                    for (ExtrasDto extrasDto : reservationDto.getExtras()) {
                        extrasService.createExtrasReservation(extrasDto, resev.getId());
                    }
                    return ReservationDto.fromEntity(resev);
                } else if (reservation == null && reservation2 != null && reservationDto.getCheckout().getTime() < reservation2.getDate().getTime()) {
                    r.setCreationDate(Instant.now());
                    r.setUser(userRepository.findById(iduser).orElseThrow());
                    r.setOffer(offer);
                    r.setCount_people(reservationDto.getCount_people());
                    r.setState(State.Confirmed);
                    Reservation resev = reservationRepository.save(r);
                    for (ExtrasDto extrasDto : reservationDto.getExtras()) {
                        extrasService.createExtrasReservation(extrasDto, resev.getId());
                    }
                    return ReservationDto.fromEntity(resev);
                } else if (reservation != null && reservation2 != null && reservationDto.getDate().getTime() > reservation.getCheckout().getTime() && reservationDto.getCheckout().getTime() < reservation2.getDate().getTime()) {
                    r.setCreationDate(Instant.now());
                    r.setUser(userRepository.findById(iduser).orElseThrow());
                    r.setOffer(offer);
                    r.setCount_people(reservationDto.getCount_people());
                    r.setState(State.Confirmed);
                    Reservation resev = reservationRepository.save(r);
                    for (ExtrasDto extrasDto : reservationDto.getExtras()) {
                        extrasService.createExtrasReservation(extrasDto, resev.getId());
                    }
                    return ReservationDto.fromEntity(resev);
                } else {
                    throw new RuntimeException("These dates are already booked from " + reservation.getDate() + " to " + reservation.getCheckout() + ", please choose another ones");
                }
            }
        }
        if (offer.getGeneric_Type().equals("event") || offer.getGeneric_Type().equals("program")) {
            Integer count = reservationRepository.getCountPeopleByOffer(offer);
            if (count == null) {
                count = 0;
            }
            if (reservationDto.getCount_people() == 0) {
                throw new RuntimeException("Please enter a valid number.");
            }
            if (count + reservationDto.getCount_people() > offer.getCapacity()) {
                throw new RuntimeException((offer.getCapacity() - count) + " places are left for this offer");
            } else {
                r.setCreationDate(Instant.now());
                r.setUser(userRepository.findById(iduser).orElseThrow());
                r.setOffer(offer);
                r.setCount_people(reservationDto.getCount_people());
                r.setState(State.Confirmed);
                Reservation resev = reservationRepository.save(r);
                return ReservationDto.fromEntity(resev);
            }

        }

        r.setCreationDate(Instant.now());
        r.setUser(userRepository.findById(iduser).orElseThrow());
        r.setOffer(offer);
        r.setCount_people(reservationDto.getCount_people());
        r.setState(State.Confirmed);
        Reservation resev = reservationRepository.save(r);
        return ReservationDto.fromEntity(resev);

    }

    public void setReservationUnpayed(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with id " + reservationId));
        reservation.setPayed(true);
        reservationRepository.save(reservation);
    }
}
