package com.tousime_alternative.service;

import com.tousime_alternative.dto.ArtisanDto;

import java.util.List;
import java.util.Optional;

public interface ArtisanService {
    ArtisanDto update(ArtisanDto artisanDto);

    Optional<ArtisanDto> findById(Long id);

    List<ArtisanDto> findAll();

    List<ArtisanDto> findAllByPartnerId(long id);

    void delete(Long id);

    ArtisanDto createArtisan(ArtisanDto artisanDt, long id);
}
