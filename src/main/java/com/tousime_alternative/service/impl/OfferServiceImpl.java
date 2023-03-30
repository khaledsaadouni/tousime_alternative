package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.OfferDto;
import com.tousime_alternative.model.Offer;
import com.tousime_alternative.repository.OfferRepository;
import com.tousime_alternative.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private OfferRepository offerRepository;
    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;

    }

    @Override
    public OfferDto update(OfferDto dto) {
        Offer offer= offerRepository.findById(dto.getId()).orElseThrow();
        offer.setId(dto.getId());
        offer.setName(dto.getName());
        offer.setCapacity(dto.getCapacity());
        offer.setEmplacement(dto.getEmplacement());
        offer.setPhoto(dto.getPhoto());
        offer.setSocialMediaLink(dto.getSocialMediaLink());
        offer.setPhoto(dto.getType());
        var offerr= offerRepository.save(offer);
        return OfferDto.fromEntity(offerr);
    }

    @Override
    public Optional<OfferDto> findById(Long id) {
        return offerRepository.findById(id).map(OfferDto::fromEntity).stream().findFirst();
    }

    @Override
    public List<OfferDto> findAll() {
        return offerRepository.findAll().stream().map(OfferDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public OfferDto createOffer(OfferDto dto) {
        Offer offer=new Offer();
        offer.setId(dto.getId());
        offer.setName(dto.getName());
        offer.setCapacity(dto.getCapacity());
        offer.setEmplacement(dto.getEmplacement());
        offer.setPhoto(dto.getPhoto());
        offer.setSocialMediaLink(dto.getSocialMediaLink());
        offer.setPhoto(dto.getType());
        var o= offerRepository.save(offer);
        return OfferDto.fromEntity(o);

    }
}
