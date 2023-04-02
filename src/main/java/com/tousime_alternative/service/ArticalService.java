package com.tousime_alternative.service;

import com.tousime_alternative.dto.ArticalDto;

import java.util.List;
import java.util.Optional;

public interface ArticalService {
    ArticalDto update(ArticalDto artisanDto);

    Optional<ArticalDto> findById(Long id);

    List<ArticalDto> findAllByShop(long id);

    void delete(Long id);

    ArticalDto createArtical(ArticalDto artisanDto, long id);
}
