package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.ArtisanDto;
import com.tousime_alternative.model.Artisan;
import com.tousime_alternative.repository.ArtisanRepository;
import com.tousime_alternative.repository.PartnerRepository;
import com.tousime_alternative.service.ArtisanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@Slf4j
public class ArtisanServiceImpl implements ArtisanService {
    private ArtisanRepository artisanRepository;
    private PartnerRepository partnerRepository;

    @Autowired
    public ArtisanServiceImpl(ArtisanRepository artisanRepository, PartnerRepository partnerRepository) {
        this.artisanRepository = artisanRepository;
        this.partnerRepository = partnerRepository;
    }


    @Override
    public ArtisanDto update(ArtisanDto artisanDto) {
        Artisan artisan = artisanRepository.findById(artisanDto.getId()).orElseThrow();
        artisan.setDestination(artisanDto.getDestination());
        artisan.setGoogle_map(artisan.getGoogle_map());
        artisan.setEmplacement(artisanDto.getEmplacement());
        artisan.setOpening_hour(artisanDto.getOpening_hour());
        artisan.setDescription(artisanDto.getDescription());
        artisan.setClosing_hour(artisanDto.getClosing_hour());
        artisan.setCommercial_name(artisanDto.getCommercial_name());
        artisan.setPhoto(artisanDto.getPhoto());
        artisan.setType(artisanDto.getType());
        artisan.setLastModifiedDate(Instant.now());
        return ArtisanDto.fromEntity(artisanRepository.save(artisan));
    }

    @Override
    public Optional<ArtisanDto> findById(Long id) {
        return artisanRepository.findById(id)
                .map(ArtisanDto::fromEntity)
                .stream().findFirst();
    }

    @Override
    public List<ArtisanDto> findAll() {
        return artisanRepository.findAll().stream()
                .map(ArtisanDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArtisanDto> findAllByPartnerId(long id) {
        return artisanRepository.findAllByPartnerId(id)
                .stream()
                .map(ArtisanDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        artisanRepository.deleteById(id);
    }

    @Override
    public ArtisanDto createArtisan(ArtisanDto artisanDto, long id) {
        Artisan artisan = ArtisanDto.toEntity(artisanDto);
        artisan.setCreationDate(Instant.now());
        artisan.setPartner(partnerRepository.findById(id).orElseThrow());
        return ArtisanDto.fromEntity(artisanRepository.save(artisan));
    }
}
