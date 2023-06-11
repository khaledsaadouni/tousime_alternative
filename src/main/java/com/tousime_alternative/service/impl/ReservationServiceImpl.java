package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.ReservationDto;
import com.tousime_alternative.exception.EntityNotFoundException;
import com.tousime_alternative.model.Offer;
import com.tousime_alternative.model.Reservation;
import com.tousime_alternative.model.enumr.State;
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
            Reservation reservation = reservationRepository.findLastReservationByOfferAndDate(offer, reservationDto.getDate());
            Reservation reservation2 = reservationRepository.findFirstReservationByOfferAndDate(offer, reservationDto.getDate());
            if(reservation2==null){
              System.out.println("r2 null");
            }else{
                System.out.println("r2 not null");
                System.out.println(reservation2.getDate());
            } if(reservation==null){
              System.out.println("r null");
            }else{
                System.out.println("r not null");

                System.out.println(reservation.getDate());
            }
            if (reservation == null && reservation2 == null) {
                r.setCreationDate(Instant.now());
                r.setUser(userRepository.findById(iduser).orElseThrow());
                r.setOffer(offer);
                r.setCount_people(reservationDto.getCount_people());
                r.setState(State.Pending);
                return ReservationDto.fromEntity(reservationRepository.save(r));
            } else if (reservation != null && reservation2 == null && reservationDto.getDate().getTime() > reservation.getCheckout().getTime()) {
                r.setCreationDate(Instant.now());
                r.setUser(userRepository.findById(iduser).orElseThrow());
                r.setOffer(offer);
                r.setCount_people(reservationDto.getCount_people());
                r.setState(State.Pending);
                return ReservationDto.fromEntity(reservationRepository.save(r));
            } else if (reservation == null && reservation2 != null && reservationDto.getCheckout().getTime() < reservation2.getDate().getTime()) {
                r.setCreationDate(Instant.now());
                r.setUser(userRepository.findById(iduser).orElseThrow());
                r.setOffer(offer);
                r.setCount_people(reservationDto.getCount_people());
                r.setState(State.Pending);
                return ReservationDto.fromEntity(reservationRepository.save(r));
            } else if (reservation != null && reservation2 != null && reservationDto.getDate().getTime() > reservation.getCheckout().getTime() && reservationDto.getCheckout().getTime() < reservation2.getDate().getTime()) {
                r.setCreationDate(Instant.now());
                r.setUser(userRepository.findById(iduser).orElseThrow());
                r.setOffer(offer);
                r.setCount_people(reservationDto.getCount_people());
                r.setState(State.Pending);
                return ReservationDto.fromEntity(reservationRepository.save(r));
            } else {
                throw new RuntimeException("These dates are already booked from " + reservation.getDate() + " to " + reservation.getCheckout() + ", please choose another ones");
            }
        }
        if (offer.getGeneric_Type().equals("event")) {
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
                r.setState(State.Pending);
                return ReservationDto.fromEntity(reservationRepository.save(r));
            }

        }

        r.setCreationDate(Instant.now());
        r.setUser(userRepository.findById(iduser).orElseThrow());
        r.setOffer(offer);
        r.setCount_people(reservationDto.getCount_people());
        r.setState(State.Pending);
        return ReservationDto.fromEntity(reservationRepository.save(r));

    }
    public void setReservationUnpayed(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with id " + reservationId));
        reservation.setPayed(true);
        reservationRepository.save(reservation);
    }
}
