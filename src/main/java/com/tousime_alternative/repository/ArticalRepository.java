package com.tousime_alternative.repository;

import com.tousime_alternative.model.Artical;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticalRepository extends JpaRepository<Artical, Long> {
    List<Artical> findAllByArtisanId(long id);
}
