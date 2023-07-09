package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.ActivityDto;
import com.tousime_alternative.model.Activity;
import com.tousime_alternative.repository.ActivityRepository;
import com.tousime_alternative.repository.ProgramRepository;
import com.tousime_alternative.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {
    private ActivityRepository activityRepository;
    private ProgramRepository programRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository, ProgramRepository programRepository) {
        this.activityRepository = activityRepository;
        this.programRepository = programRepository;
    }

    @Override
    public ActivityDto update(ActivityDto dto) {
        return null;
    }

    @Override
    public List<ActivityDto> findByProgram(Long id) {
        return activityRepository.findAllByProgramId(id).stream().map(ActivityDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        activityRepository.deleteById(id);
    }

    @Override
    public ActivityDto createActivity(ActivityDto dto, long id) {
        Activity activity = ActivityDto.toEntity(dto);
        activity.setProgram(programRepository.findById(id).orElseThrow());
        return ActivityDto.fromEntity(activityRepository.save(activity));
    }
}
