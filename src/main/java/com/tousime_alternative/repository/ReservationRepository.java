package com.tousime_alternative.repository;

import com.tousime_alternative.model.Reservation;
import com.tousime_alternative.model.enumr.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUserId(long id);

    List<Reservation> findAllByOfferId(long id);

    Integer countByStateAndOfferId(State state, long id);

}
