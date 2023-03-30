package com.tousime_alternative.controller;

import com.tousime_alternative.dto.EventDto;
import com.tousime_alternative.model.Event;
import com.tousime_alternative.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.HttpStatus.CREATED;

@RestController()
@RequestMapping("/api/v1/event")
public class EventController {
    private EventService eventService;
    @PostMapping("/add")
    public ResponseEntity<Event> add(@RequestBody EventDto dto){
        return new ResponseEntity(eventService.createEvent(dto), CREATED);
    }
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @GetMapping("/all")
    public List<EventDto> findAll() {
        return eventService.findAll();
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
