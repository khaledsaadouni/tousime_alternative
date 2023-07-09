package com.tousime_alternative.service;

import com.tousime_alternative.dto.ActivityDto;

import java.util.List;

public interface ActivityService {
    ActivityDto update(ActivityDto dto);

    List<ActivityDto> findByProgram(Long id);

    void delete(Long id);


    ActivityDto createActivity(ActivityDto dto, long id);
}
