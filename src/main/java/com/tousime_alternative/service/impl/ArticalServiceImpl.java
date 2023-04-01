package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.ArticalDto;
import com.tousime_alternative.model.Artical;
import com.tousime_alternative.repository.ArticalRepository;
import com.tousime_alternative.repository.ArtisanRepository;
import com.tousime_alternative.service.ArticalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticalServiceImpl implements ArticalService {
    private ArticalRepository articalRepository;

    private ArtisanRepository artisanRepository;

    @Autowired
    public ArticalServiceImpl(ArticalRepository articalRepository, ArtisanRepository artisanRepository) {
        this.articalRepository = articalRepository;
        this.artisanRepository = artisanRepository;
    }

    @Override
    public ArticalDto update(ArticalDto articalDto) {
        Artical artical = ArticalDto.toEntity(articalDto);
        return ArticalDto.fromEntity(articalRepository.save(artical));

    }

    @Override
    public Optional<ArticalDto> findById(Long id) {
        return articalRepository.findById(id)
                .map(ArticalDto::fromEntity)
                .stream().findFirst();
    }

    @Override
    public List<ArticalDto> findAll() {
        return articalRepository.findAll()
                .stream()
                .map(ArticalDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        articalRepository.deleteById(id);
    }

    @Override
    public ArticalDto createArtical(ArticalDto articalDto, long id) {
        Artical artical = ArticalDto.toEntity(articalDto);
        artical.setArtisan(artisanRepository.findById(id).orElseThrow());
        return ArticalDto.fromEntity(articalRepository.save(artical));
    }
}
