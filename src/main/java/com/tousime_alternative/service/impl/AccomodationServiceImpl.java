package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.AccomodationDto;
import com.tousime_alternative.dto.ExtrasDto;
import com.tousime_alternative.model.Accomodation;
import com.tousime_alternative.model.Extras;
import com.tousime_alternative.repository.AccomodationRepository;
import com.tousime_alternative.repository.PartnerRepository;
import com.tousime_alternative.service.AccomodationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccomodationServiceImpl implements AccomodationService {
    private AccomodationRepository accomodationRepository;
    private PartnerRepository partnerRepository;
    private ExtrasServiceImpl extrasService;

    @Autowired
    public AccomodationServiceImpl(AccomodationRepository accomodationRepository,
                                   ExtrasServiceImpl extrasService,
                                   PartnerRepository partnerRepository) {
        this.extrasService = extrasService;
        this.accomodationRepository = accomodationRepository;
        this.partnerRepository = partnerRepository;

    }

    @Override
    public AccomodationDto update(AccomodationDto dto) {
        Accomodation accomodation = accomodationRepository.findById(dto.getId()).orElseThrow();
        for (Extras extra : accomodation.getExtras()
        ) {
            extrasService.delete(extra.getId());

        }
        accomodation.setExtras(null);
        accomodationRepository.save(accomodation);
        accomodation.setGeneric_Type("accomodation");
        accomodation.setDescription(dto.getDescription());
        accomodation.setGoogle_map(dto.getGoogle_map());
        accomodation.setName(dto.getName());
        accomodation.setCapacity(dto.getCapacity());
        accomodation.setEmplacement(dto.getEmplacement());
        accomodation.setPhoto(dto.getPhoto());
        accomodation.setSocialMediaLinks(dto.getSocialMediaLink());
        accomodation.setType(dto.getType());
        accomodation.setComodityList(dto.getComodityList());
        accomodation.setRegulations(dto.getRegulations());
        accomodation.setPrice(dto.getPrice());
        accomodation.setPromotion(dto.getPromotion());
        accomodation.setDestination(dto.getDestination());
        accomodation.setAllow_many_reservation(dto.getAllow_many_reservation());
        for (ExtrasDto extrasDto : dto.getExtras()) {
            extrasService.createExtras(extrasDto, accomodation.getId());
        }
        return AccomodationDto.fromEntity(accomodationRepository.save(accomodation));
    }

    @Override
    public AccomodationDto findById(Long id) {
        return accomodationRepository.findById(id).map(AccomodationDto::fromEntity).orElseThrow();
    }

    @Override
    public List<AccomodationDto> findByPartnerId(Long id) {
        return accomodationRepository.findAllByPartnerId(id).stream().map(AccomodationDto::fromEntity).collect(Collectors.toList());
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
        accomodation.setGeneric_Type("accomodation");
        accomodation.setCreationDate(Instant.now());
        accomodation.setPartner(partnerRepository.findById(id).orElseThrow());
        Accomodation accomodation_saved = accomodationRepository.save(accomodation);
        for (ExtrasDto extra : dto.getExtras()
        ) {
            extrasService.createExtras(extra, accomodation_saved.getId());
        }
        return AccomodationDto.fromEntity(accomodationRepository.findById(accomodation_saved.getId()).orElseThrow());
    }
}
