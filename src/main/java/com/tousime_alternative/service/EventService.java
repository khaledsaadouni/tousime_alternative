package com.tousime_alternative.service;

import com.tousime_alternative.dto.EventDto;

import java.util.List;
import java.util.Optional;

public interface EventService {
    EventDto update(EventDto dto);

    Optional<EventDto> findById(Long id);

    List<EventDto> findAll();

    void delete(Long id);


    EventDto createEvent(EventDto dto);

}
