package com.tousime_alternative.repository;

import com.tousime_alternative.model.Offer;
import com.tousime_alternative.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUserId(long id);

    List<Reservation> findAllByOfferPartnerId(long id);

    List<Reservation> findAllByOfferId(Long id);

    @Query("SELECT r FROM Reservation r WHERE  r.offer = :offer AND r.date<= :date ORDER BY r.date DESC LIMIT 1")
    Reservation findLastReservationByOfferAndDate(@Param("offer") Offer offer, @Param("date") Date date);

    @Query("SELECT r FROM Reservation r WHERE  r.offer = :offer AND r.date>= :date ORDER BY r.date ASC LIMIT 1")
    Reservation findFirstReservationByOfferAndDate(@Param("offer") Offer offer, @Param("date") Date date);

    @Query("SELECT SUM(r.count_people) FROM Reservation r WHERE r.offer = :offer AND r.state != 'Canceled' ")
    Integer getCountPeopleByOffer(@Param("offer") Offer offer);

}
