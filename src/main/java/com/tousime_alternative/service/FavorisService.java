package com.tousime_alternative.service;


import com.tousime_alternative.dto.FavorisDto;

import java.util.List;
import java.util.Optional;

public interface FavorisService {
    FavorisDto update(FavorisDto favorisDto);

    Optional<FavorisDto> findById(Long id);

    List<FavorisDto> findAll();
    void delete(Long id);

}
