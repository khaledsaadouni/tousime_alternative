package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.EventDto;
import com.tousime_alternative.model.Event;
import com.tousime_alternative.repository.EventRepository;
import com.tousime_alternative.repository.PartnerRepository;
import com.tousime_alternative.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;
    private PartnerRepository partnerRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository,
                            PartnerRepository partnerRepository) {
        this.eventRepository = eventRepository;
        this.partnerRepository = partnerRepository;

    }

    @Override
    public EventDto update(EventDto dto) {
        Event event = EventDto.toEntity(dto);
        return EventDto.fromEntity(eventRepository.save(event));
    }

    @Override
    public Optional<EventDto> findById(Long id) {
        return eventRepository.findById(id).map(EventDto::fromEntity).stream().findFirst();
    }

    @Override
    public List<EventDto> findAll() {
        return eventRepository.findAll().stream().map(EventDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        eventRepository.deleteById(id);
    }


    @Override
    public EventDto createEvent(EventDto dto, long id) {
        Event event = EventDto.toEntity(dto);
        event.setCreationDate(Instant.now());
        event.setPartner(partnerRepository.findById(id).orElseThrow());
        return EventDto.fromEntity(eventRepository.save(event));
    }
}
