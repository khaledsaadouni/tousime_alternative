package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.ActivityDto;
import com.tousime_alternative.dto.ProgramDto;
import com.tousime_alternative.model.Activity;
import com.tousime_alternative.model.Program;
import com.tousime_alternative.repository.PartnerRepository;
import com.tousime_alternative.repository.ProgramRepository;
import com.tousime_alternative.service.ActivityService;
import com.tousime_alternative.service.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProgramServiceImpl implements ProgramService {
    private PartnerRepository partnerRepository;
    private ProgramRepository programRepository;
    private ActivityService activityService;

    @Autowired
    public ProgramServiceImpl(PartnerRepository partnerRepository, ProgramRepository programRepository, ActivityService activityService) {
        this.partnerRepository = partnerRepository;
        this.programRepository = programRepository;
        this.activityService = activityService;
    }

    @Override
    public ProgramDto update(ProgramDto dto) {
        Program program = programRepository.findById(dto.getId()).orElseThrow();
        for (Activity actvity : program.getActivities()
        ) {
            activityService.delete(actvity.getId());

        }
        program.setActivities(null);
        programRepository.save(program);
        program.setGeneric_Type("program");
        program.setDescription(dto.getDescription());
        program.setGoogle_map(dto.getGoogle_map());
        program.setName(dto.getName());
        program.setCapacity(dto.getCapacity());
        program.setEmplacement(dto.getEmplacement());
        program.setPhoto(dto.getPhoto());
        program.setPrice(dto.getPrice());
        program.setStartDate(dto.getStartDate());
        program.setEndDate(dto.getEndDate());
        program.setDestination(dto.getDestination());
        for (ActivityDto actvity : dto.getActivities()
        ) {
            activityService.createActivity(actvity, program.getId());
        }
        return ProgramDto.fromEntity(programRepository.save(program));
    }

    @Override
    public List<ProgramDto> findByPartnerId(Long id) {
        return programRepository.findAllByPartnerId(id).stream().map(ProgramDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ProgramDto findById(Long id) {
        return ProgramDto.fromEntity(programRepository.findById(id).orElseThrow());
    }

    @Override
    public List<ProgramDto> findAll() {
        return programRepository.findAll().stream().map(ProgramDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        programRepository.deleteById(id);
    }

    @Override
    public ProgramDto createProgram(ProgramDto dto, long id) {
        Program program = ProgramDto.toEntity(dto);
        program.setGeneric_Type("program");
        program.setCreationDate(Instant.now());
        program.setPartner(partnerRepository.findById(id).orElseThrow());
        Program program_saved = programRepository.save(program);
        for (ActivityDto activityDto : dto.getActivities()
        ) {
            activityService.createActivity(activityDto, program_saved.getId());
        }
        return ProgramDto.fromEntity(programRepository.findById(program_saved.getId()).orElseThrow());
    }
}
