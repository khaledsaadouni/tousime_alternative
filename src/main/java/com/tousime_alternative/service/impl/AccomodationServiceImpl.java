package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.AccomodationDto;
import com.tousime_alternative.model.Accomodation;
import com.tousime_alternative.repository.AccomodationRepository;
import com.tousime_alternative.repository.PartnerRepository;
import com.tousime_alternative.service.AccomodationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccomodationServiceImpl implements AccomodationService {
    private AccomodationRepository accomodationRepository;
    private PartnerRepository partnerRepository;

    @Autowired
    public AccomodationServiceImpl(AccomodationRepository accomodationRepository,
                                   PartnerRepository partnerRepository) {
        this.accomodationRepository = accomodationRepository;
        this.partnerRepository = partnerRepository;

    }

    @Override
    public AccomodationDto update(AccomodationDto dto) {
        Accomodation accomodation = AccomodationDto.toEntity(dto);
        return AccomodationDto.fromEntity(accomodationRepository.save(accomodation));
    }

    @Override
    public AccomodationDto  findById(Long id) {
        return accomodationRepository.findById(id).map(AccomodationDto::fromEntity).orElseThrow();
    }

    @Override
    public List<AccomodationDto> findAll() {
        return accomodationRepository.findAll().stream().map(AccomodationDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        accomodationRepository.deleteById(id);
    }


    @Override
    public AccomodationDto createAccomodation(AccomodationDto dto, long id) {
        Accomodation accomodation = AccomodationDto.toEntity(dto);
        accomodation.setPartner(partnerRepository.findById(id).orElseThrow());
        return AccomodationDto.fromEntity(accomodationRepository.save(accomodation));
    }
}
