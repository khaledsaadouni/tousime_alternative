package com.tousime_alternative.repository;

import com.tousime_alternative.model.Artisan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtisanRepository extends JpaRepository<Artisan, Long> {
    List<Artisan> findAllByPartnerId(long id);
}
