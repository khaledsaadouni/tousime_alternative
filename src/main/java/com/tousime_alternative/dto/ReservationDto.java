package com.tousime_alternative.dto;

import com.tousime_alternative.model.Reservation;
import com.tousime_alternative.model.enumr.State;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class ReservationDto {
    private long id;
    private Date date;
    private State state;
    private Integer count_people;
    private Date checkout;
    private float price;
    private UserDto user;
    private OfferDto offer;
private boolean payed;
    public static ReservationDto fromEntity(Reservation reservation) {
        if (reservation == null) {
            return null;
        }

        return ReservationDto.builder()
                .id(reservation.getId())
                .date(reservation.getDate())
                .checkout(reservation.getCheckout())
                .price(reservation.getPrice())
                .state(reservation.getState())
                .count_people(reservation.getCount_people())
                .user(UserDto.fromEntity(reservation.getUser()))
                .offer(OfferDto.fromEntity(reservation.getOffer()))
                .payed(reservation.isPayed())
                .build();
    }

    public static Reservation toEntity(ReservationDto reservationDto) {
        if (reservationDto == null) {
            return null;
        }

        Reservation reservation = new Reservation();
        reservation.setId(reservationDto.getId());
        reservation.setPrice(reservationDto.getPrice());
        reservation.setCheckout(reservationDto.getCheckout());
        reservation.setDate(reservationDto.getDate());
        reservation.setState(reservationDto.getState());
        reservation.setCount_people(reservationDto.getCount_people());
        reservation.setPayed((reservationDto.isPayed()));
        return reservation;

    }
}
