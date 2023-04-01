package com.tousime_alternative.repository;

import com.tousime_alternative.model.Accomodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation, Long> {
    Optional<Accomodation> findById(Long id);
}
