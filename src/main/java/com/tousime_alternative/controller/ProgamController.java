package com.tousime_alternative.controller;

import com.tousime_alternative.dto.ProgramDto;
import com.tousime_alternative.service.ProgramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/program")
public class ProgamController {
    private ProgramService programService;

    public ProgamController(ProgramService programService) {
        this.programService = programService;
    }

    @PostMapping("/add/{idPartner}")
    public ResponseEntity<ProgramDto> add(@RequestBody ProgramDto dto, @PathVariable("idPartner") long id) {
        return ResponseEntity.ok(programService.createProgram(dto, id));
    }

    @GetMapping("/partner/{idpartner}")
    public List<ProgramDto> findByPartner(@PathVariable("idpartner") Long id) {
        return programService.findByPartnerId(id);
    }

    @GetMapping("/all")
    public List<ProgramDto> findAll() {
        return programService.findAll();
    }

    @GetMapping("/{idAccom}")
    public ResponseEntity<ProgramDto> findById(@PathVariable("idAccom") long id) {
        return ResponseEntity.ok(programService.findById(id));
    }

    @PostMapping("/update")
    public ResponseEntity<ProgramDto> update(@RequestBody ProgramDto dto) {
        return ResponseEntity.ok(programService.update(dto));
    }

    @DeleteMapping("/delete/{idAccomodation}")
    public void delete(@PathVariable("idAccomodation") long id) {
        programService.delete(id);
    }
}
