package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.ExtrasDto;
import com.tousime_alternative.model.Extras;
import com.tousime_alternative.repository.AccomodationRepository;
import com.tousime_alternative.repository.ExtrasRepository;
import com.tousime_alternative.repository.ReservationRepository;
import com.tousime_alternative.service.ExtrasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExtrasServiceImpl implements ExtrasService {
    private ExtrasRepository extrasRepository;
    private AccomodationRepository accomodationRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ExtrasServiceImpl(ExtrasRepository extrasRepository, AccomodationRepository accomodationRepository, ReservationRepository reservationRepository) {
        this.extrasRepository = extrasRepository;
        this.reservationRepository = reservationRepository;
        this.accomodationRepository = accomodationRepository;
    }

    @Override
    public ExtrasDto update(ExtrasDto dto) {
        return null;
    }

    @Override
    public List<ExtrasDto> findByAccomodation(Long id) {
        return extrasRepository.findAllByAccomodationId(id).stream().map(ExtrasDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        extrasRepository.deleteById(id);
    }

    @Override
    public ExtrasDto createExtras(ExtrasDto dto, long id) {
        Extras extras = ExtrasDto.toEntity(dto);
        extras.setAccomodation(accomodationRepository.findById(id).orElseThrow());
        return ExtrasDto.fromEntity(extrasRepository.save(extras));
    }

    @Override
    public ExtrasDto createExtrasReservation(ExtrasDto dto, long id) {
        Extras extras = new Extras();
        extras.setPrice(dto.getPrice());
        extras.setName(dto.getName());
        extras.setReservation(reservationRepository.findById(id).orElseThrow());
        return ExtrasDto.fromEntity(extrasRepository.save(extras));
    }
}
