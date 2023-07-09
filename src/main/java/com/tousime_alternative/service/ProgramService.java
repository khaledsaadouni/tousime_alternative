package com.tousime_alternative.service;

import com.tousime_alternative.dto.ProgramDto;

import java.util.List;

public interface ProgramService {
    ProgramDto update(ProgramDto dto);

    List<ProgramDto> findByPartnerId(Long id);

    ProgramDto findById(Long id);

    List<ProgramDto> findAll();

    void delete(Long id);


    ProgramDto createProgram(ProgramDto dto, long id);
}
