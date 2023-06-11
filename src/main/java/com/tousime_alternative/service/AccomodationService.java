package com.tousime_alternative.service;

import com.tousime_alternative.dto.AccomodationDto;

import java.util.List;
import java.util.Optional;

public interface AccomodationService {
    AccomodationDto update(AccomodationDto dto);

    List<AccomodationDto> findByPartnerId(Long id);

    AccomodationDto findById(Long id);

    List<AccomodationDto> findAll();

    void delete(Long id);


    AccomodationDto createAccomodation(AccomodationDto dto, long id);
}
