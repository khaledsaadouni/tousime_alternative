package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.PartnerDto;
import com.tousime_alternative.dto.UpdatePasswordDto;
import com.tousime_alternative.model.Partner;
import com.tousime_alternative.repository.PartnerRepository;
import com.tousime_alternative.service.PartnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PartnerServiceImpl implements PartnerService {
    private PasswordEncoder passwordEncoder;
    private PartnerRepository partnerRepository;

    @Autowired
    public PartnerServiceImpl(PartnerRepository partnerRepository,
                              PasswordEncoder passwordEncoder) {
        this.partnerRepository = partnerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private boolean userAlreadyExists(String email) {
        Optional<Partner> user = partnerRepository.findByEmail(email);
        return user.isPresent();
    }

    @Override
    public PartnerDto update(PartnerDto dto) {
        Partner user = partnerRepository.findById(dto.getId()).orElseThrow();
        user.setLastname(dto.getLastname());
        user.setFirstname(dto.getFirstname());
        user.setEmail(dto.getEmail());
        user.setRIB(dto.getRIB());
        user.setBirthday(dto.getBirthday());
        user.setRole(dto.getRole());
        user.setPhoto(dto.getPhoto());
        user.setPhone(dto.getPhone());
        user.setCommercial_name(dto.getCommercial_name());
        user.setAddress(dto.getAddress());
        user.setLastModifiedDate(Instant.now());
        var user2 = partnerRepository.save(user);
        return PartnerDto.fromEntity(user2);
    }

    public Optional<PartnerDto> findById(Long id) {
        return partnerRepository.findById(id)
                .map(PartnerDto::fromEntity)
                .stream().findFirst();
    }

    @Override
    public List<PartnerDto> findAll() {
        return partnerRepository.findAll().stream()
                .map(PartnerDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        partnerRepository.deleteById(id);
    }

    @Override
    public PartnerDto findByEmail(String email) {
        return PartnerDto.fromEntity(partnerRepository.findByEmail(email).orElseThrow());
    }

    @Override
    public PartnerDto updatePassword(UpdatePasswordDto dto) {
        Partner user = partnerRepository.findById(dto.getId()).orElseThrow();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setLastModifiedDate(Instant.now());
        user = partnerRepository.save(user);
        return PartnerDto.fromEntity(user);
    }

}
