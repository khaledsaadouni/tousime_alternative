package com.tousime_alternative.repository;

import com.tousime_alternative.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation , Long> {

}
