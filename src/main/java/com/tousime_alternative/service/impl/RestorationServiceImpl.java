package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.RestorationDto;
import com.tousime_alternative.model.Restoration;
import com.tousime_alternative.repository.PartnerRepository;
import com.tousime_alternative.repository.RestaurationRepository;
import com.tousime_alternative.service.RestorationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RestorationServiceImpl implements RestorationService {
    private RestaurationRepository restaurationRepository;
    private PartnerRepository partnerRepository;

    @Autowired
    public RestorationServiceImpl(RestaurationRepository restaurationRepository,
                                  PartnerRepository partnerRepository) {
        this.restaurationRepository = restaurationRepository;
        this.partnerRepository = partnerRepository;

    }

    @Override
    public RestorationDto update(RestorationDto dto) {
        Restoration restauration = RestorationDto.toEntity(dto);
        return RestorationDto.fromEntity(restaurationRepository.save(restauration));
    }

    @Override
    public Optional<RestorationDto> findById(Long id) {
        return restaurationRepository.findById(id).map(RestorationDto::fromEntity).stream().findFirst();
    }

    @Override
    public List<RestorationDto> findAll() {
        return restaurationRepository.findAll().stream().map(RestorationDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        restaurationRepository.deleteById(id);
    }

    @Override
    public RestorationDto createRestauration(RestorationDto dto, long id) {
        Restoration restauration = RestorationDto.toEntity(dto);
        restauration.setPartner(partnerRepository.findById(id).orElseThrow());
        return RestorationDto.fromEntity(restaurationRepository.save(restauration));
    }
}
