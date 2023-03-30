package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.EventDto;
import com.tousime_alternative.repository.EventRepository;
import com.tousime_alternative.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import com.tousime_alternative.model.Event;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;
    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;

    }
    @Override
    public EventDto update(EventDto dto) {
        Event event= eventRepository.findById(dto.getId()).orElseThrow();
        event.setId(dto.getId());
        event.setName(dto.getName());
        event.setCapacity(dto.getCapacity());
        event.setEmplacement(dto.getEmplacement());
        event.setPhoto(dto.getPhoto());
        event.setSocialMediaLink(dto.getSocialMediaLink());
        event.setPhoto(dto.getType());
        event.setEventDate(dto.getEventDate());
        event.setRegulations(dto.getRegulations());
        event.setDuration(dto.getDuration());
        var eventr= eventRepository.save(event);
        return EventDto.fromEntity(eventr);
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
    public EventDto createEvent(EventDto dto) {
        Event event=new Event();
        event.setId(dto.getId());
        event.setName(dto.getName());
        event.setCapacity(dto.getCapacity());
        event.setEmplacement(dto.getEmplacement());
        event.setPhoto(dto.getPhoto());
        event.setSocialMediaLink(dto.getSocialMediaLink());
        event.setPhoto(dto.getType());
        event.setEventDate(dto.getEventDate());
        event.setRegulations(dto.getRegulations());
        event.setDuration(dto.getDuration());
        var o= eventRepository.save(event);
        return EventDto.fromEntity(o);
    }
}
