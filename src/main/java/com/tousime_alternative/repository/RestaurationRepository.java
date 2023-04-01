package com.tousime_alternative.repository;

import com.tousime_alternative.model.Restoration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurationRepository extends JpaRepository<Restoration, Long> {
    Optional<Restoration> findById(Long id);
}
