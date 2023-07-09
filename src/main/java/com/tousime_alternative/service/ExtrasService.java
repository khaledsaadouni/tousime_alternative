package com.tousime_alternative.service;

import com.tousime_alternative.dto.ExtrasDto;

import java.util.List;

public interface ExtrasService {
    ExtrasDto update(ExtrasDto dto);

    List<ExtrasDto> findByAccomodation(Long id);

    void delete(Long id);


    ExtrasDto createExtras(ExtrasDto dto, long id);

    ExtrasDto createExtrasReservation(ExtrasDto dto, long id);
}
