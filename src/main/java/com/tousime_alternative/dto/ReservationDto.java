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
    private State state ;
    private Integer nbre_personne ;
    public static ReservationDto fromEntity(Reservation reservation) {
        if (reservation == null) {
            return null;
        }

        return ReservationDto.builder()
                .id(reservation.getId())
                .date(reservation.getDate())
                .state(reservation.getState())
                .nbre_personne(reservation.getNbre_personne())
                .build();
    }
    public static Reservation toEntity(ReservationDto reservationDto) {
        if (reservationDto == null) {
            return null;
        }

        Reservation reservation = new Reservation();
        reservation.setId(reservationDto.getId());
        reservation.setDate(reservationDto.getDate());
        reservation.setState(reservationDto.getState());
        reservation.setNbre_personne(reservationDto.getNbre_personne());
        return reservation;

    }
}
