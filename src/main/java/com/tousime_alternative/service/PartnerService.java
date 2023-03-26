package com.tousime_alternative.service;


import com.tousime_alternative.dto.PartnerDto;
import com.tousime_alternative.dto.UpdatePasswordDto;

import java.util.List;
import java.util.Optional;

public interface PartnerService {
    PartnerDto update(PartnerDto dto);

    Optional<PartnerDto> findById(Long id);

    List<PartnerDto> findAll();

    void delete(Long id);

    PartnerDto findByEmail(String email);

    PartnerDto updatePassword(UpdatePasswordDto dto);

}
