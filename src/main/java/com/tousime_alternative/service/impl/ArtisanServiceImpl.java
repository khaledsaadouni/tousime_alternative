package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.ArtisanDto;
import com.tousime_alternative.dto.ReservationDto;
import com.tousime_alternative.model.Artisan;
import com.tousime_alternative.model.Reservation;
import com.tousime_alternative.repository.ArtisanRepository;
import com.tousime_alternative.repository.ReservationRepository;
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

    @Autowired
    public ArtisanServiceImpl(ArtisanRepository artisanRepository) {
        this.artisanRepository = artisanRepository;

    }
    @Override
    public ArtisanDto update(ArtisanDto artisanDto) {
        Artisan artisan= artisanRepository.findById(artisanDto.getId()).orElseThrow();
        artisan.setEmplacement(artisanDto.getEmplacement());
        artisan.setHeure_ouverture(artisanDto.getHeure_ouverture());
        artisan.setHeure_fermeture(artisanDto.getHeure_fermeture());
        artisan.setNom_commercial(artisanDto.getNom_commercial());
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
    public void delete(Long id) {
        artisanRepository.deleteById(id);
    }

    @Override
    public ArtisanDto createArtisan(ArtisanDto artisanDto) {
        return ArtisanDto.fromEntity(artisanRepository.save(ArtisanDto.toEntity(artisanDto)));
    }
}
