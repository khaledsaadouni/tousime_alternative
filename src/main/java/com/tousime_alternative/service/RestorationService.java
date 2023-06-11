package com.tousime_alternative.service;


import com.tousime_alternative.dto.RestorationDto;

import java.util.List;
import java.util.Optional;

public interface RestorationService {
    RestorationDto update(RestorationDto dto);

    Optional<RestorationDto> findById(Long id);

    List<RestorationDto> findAll();
    List<RestorationDto>  findByPartnerId(Long id);
    void delete(Long id);

    RestorationDto createRestauration(RestorationDto dto, long id);
}
