package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.AccomodationDto;
import com.tousime_alternative.model.Accomodation;
import com.tousime_alternative.repository.AccomodationRepository;
import com.tousime_alternative.service.AccomodationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccomodationServiceImpl implements AccomodationService {
    private AccomodationRepository accomodationRepository;
    @Autowired
    public AccomodationServiceImpl(AccomodationRepository accomodationRepository) {
        this.accomodationRepository = accomodationRepository;

    }
    @Override
    public AccomodationDto update(AccomodationDto dto) {
        Accomodation accomodation= accomodationRepository.findById(dto.getId()).orElseThrow();
        accomodation.setId(dto.getId());
        accomodation.setName(dto.getName());
        accomodation.setCapacity(dto.getCapacity());
        accomodation.setEmplacement(dto.getEmplacement());
        accomodation.setPhoto(dto.getPhoto());
        accomodation.setSocialMediaLink(dto.getSocialMediaLink());
        accomodation.setPhoto(dto.getType());
        accomodation.setComodityList(dto.getComodityList());
        accomodation.setRegulations(dto.getRegulations());
        accomodation.setPrice(dto.getPrice());
        accomodation.setPromotion(dto.getPromotion());
        var accomodationr= accomodationRepository.save(accomodation);
        return AccomodationDto.fromEntity(accomodationr);
    }

    @Override
    public Optional<AccomodationDto> findById(Long id) {
        return accomodationRepository.findById(id).map(AccomodationDto::fromEntity).stream().findFirst();
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
    public AccomodationDto createAccomodation(AccomodationDto dto) {
        Accomodation accomodation=new Accomodation();
        accomodation.setId(dto.getId());
        accomodation.setName(dto.getName());
        accomodation.setCapacity(dto.getCapacity());
        accomodation.setEmplacement(dto.getEmplacement());
        accomodation.setPhoto(dto.getPhoto());
        accomodation.setSocialMediaLink(dto.getSocialMediaLink());
        accomodation.setPhoto(dto.getType());
        accomodation.setComodityList(dto.getComodityList());
        accomodation.setRegulations(dto.getRegulations());
        accomodation.setPrice(dto.getPrice());
        accomodation.setPromotion(dto.getPromotion());
        var o= accomodationRepository.save(accomodation);
        return AccomodationDto.fromEntity(o);
    }
}
