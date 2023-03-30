package com.tousime_alternative.service;

import com.tousime_alternative.dto.AccomodationDto;

import java.util.List;
import java.util.Optional;

public interface AccomodationService {
    AccomodationDto update(AccomodationDto dto);

    Optional<AccomodationDto> findById(Long id);

    List<AccomodationDto> findAll();

    void delete(Long id);


    AccomodationDto createAccomodation(AccomodationDto dto);
}
