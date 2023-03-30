package com.tousime_alternative.repository;

import com.tousime_alternative.model.Restauration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurationRepository extends JpaRepository<Restauration, Long> {
}
