package com.tousime_alternative.repository;

import com.tousime_alternative.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    Optional<Program> findById(Long id);

    List<Program> findAllByPartnerId(long id);
}
