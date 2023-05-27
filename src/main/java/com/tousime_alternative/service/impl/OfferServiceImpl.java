package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.OfferDto;
import com.tousime_alternative.dto.PartnerDto;
import com.tousime_alternative.model.Offer;
import com.tousime_alternative.repository.OfferRepository;
import com.tousime_alternative.service.OfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OfferServiceImpl implements OfferService {
    private OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;

    }

    @Override
    public OfferDto update(OfferDto dto) {
        Offer offer = OfferDto.toEntity(dto);
        return OfferDto.fromEntity(offerRepository.save(offer));
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
    public List<OfferDto> findRecent() {
        return offerRepository.findAllByOrderByCreationDateDesc().stream().map(OfferDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<OfferDto> findMostLiked() {
        return offerRepository.findTop6ByOrderByReviewsDesc().stream().map(OfferDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<OfferDto> findBYPartnerID(long id) {
        return offerRepository.findAllByPartnerId(id).stream().map(OfferDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public OfferDto createOffer(OfferDto dto) {
        Offer offer = OfferDto.toEntity(dto);

        return OfferDto.fromEntity(offerRepository.save(offer));

    }

}
