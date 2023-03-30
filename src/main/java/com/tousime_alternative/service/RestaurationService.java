package com.tousime_alternative.service;

import com.tousime_alternative.dto.RestaurationDto;

import java.util.List;
import java.util.Optional;

public interface RestaurationService {
    RestaurationDto update(RestaurationDto dto);

    Optional<RestaurationDto> findById(Long id);

    List<RestaurationDto> findAll();

    void delete(Long id);

    RestaurationDto createRestauration(RestaurationDto dto);
}
