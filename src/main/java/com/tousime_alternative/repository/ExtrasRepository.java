package com.tousime_alternative.repository;

import com.tousime_alternative.model.Extras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtrasRepository extends JpaRepository<Extras, Long> {
    List<Extras> findAllByAccomodationId(long id);

}
