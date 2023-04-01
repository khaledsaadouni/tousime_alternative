package com.tousime_alternative.controller;

import com.tousime_alternative.dto.EventDto;
import com.tousime_alternative.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/event")
public class EventController {
    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/add/{idPartner}")
    public ResponseEntity<EventDto> add(@RequestBody EventDto dto, @PathVariable("idPartner") long id) {
        return ResponseEntity.ok(eventService.createEvent(dto, id));
    }

    @GetMapping("/all")
    public List<EventDto> findAll() {
        return eventService.findAll();
    }

    @GetMapping("/{idEvent}")
    public ResponseEntity<EventDto> findById(@PathVariable("idEvent") long id) {
        return ResponseEntity.ok(eventService.findById(id).orElseThrow());
    }

    @PostMapping("/update")
    public ResponseEntity<EventDto> update(@RequestBody EventDto dto) {
        return ResponseEntity.ok(eventService.update(dto));
    }

    @DeleteMapping("/delete/{idEvent}")
    public void delete(@PathVariable("idEvent") Long id) {
        eventService.delete(id);
    }
}
