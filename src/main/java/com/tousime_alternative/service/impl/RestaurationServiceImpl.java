package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.RestaurationDto;
import com.tousime_alternative.model.Restauration;
import com.tousime_alternative.repository.RestaurationRepository;
import com.tousime_alternative.service.RestaurationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RestaurationServiceImpl implements RestaurationService {
    private RestaurationRepository restaurationRepository;
    @Autowired
    public RestaurationServiceImpl(RestaurationRepository restaurationRepository) {
        this.restaurationRepository = restaurationRepository;

    }
    @Override
    public RestaurationDto update(RestaurationDto dto) {
        Restauration restauration= restaurationRepository.findById(dto.getId()).orElseThrow();
        restauration.setId(dto.getId());
        restauration.setName(dto.getName());
        restauration.setCapacity(dto.getCapacity());
        restauration.setEmplacement(dto.getEmplacement());
        restauration.setPhoto(dto.getPhoto());
        restauration.setSocialMediaLink(dto.getSocialMediaLink());
        restauration.setPhoto(dto.getType());
        restauration.setClosing(dto.getClosing());
        restauration.setOpening(dto.getOpening());
        restauration.setMenu(dto.getMenu());
        var restaurationr= restaurationRepository.save(restauration);
        return RestaurationDto.fromEntity(restaurationr);
    }

    @Override
    public Optional<RestaurationDto> findById(Long id) {
        return restaurationRepository.findById(id).map(RestaurationDto::fromEntity).stream().findFirst();
    }

    @Override
    public List<RestaurationDto> findAll() {
        return restaurationRepository.findAll().stream().map(RestaurationDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        restaurationRepository.deleteById(id);
    }

    @Override
    public RestaurationDto createRestauration(RestaurationDto dto) {
        Restauration restauration=new Restauration();
        restauration.setId(dto.getId());
        restauration.setName(dto.getName());
        restauration.setCapacity(dto.getCapacity());
        restauration.setEmplacement(dto.getEmplacement());
        restauration.setPhoto(dto.getPhoto());
        restauration.setSocialMediaLink(dto.getSocialMediaLink());
        restauration.setPhoto(dto.getType());
        restauration.setClosing(dto.getClosing());
        restauration.setOpening(dto.getOpening());
        restauration.setMenu(dto.getMenu());
        var o= restaurationRepository.save(restauration);
        return RestaurationDto.fromEntity(o);
    }
}
