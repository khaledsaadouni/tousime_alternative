package com.tousime_alternative.service;

import com.tousime_alternative.dto.OfferDto;

import java.util.List;
import java.util.Optional;

public interface OfferService {
    OfferDto update(OfferDto dto);
    Optional<OfferDto> findById(Long id);
    List<OfferDto> findAll();
    void delete(Long id);
    OfferDto createOffer(OfferDto dto);
}
