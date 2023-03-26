package com.tousime_alternative.controller;

import com.tousime_alternative.dto.PartnerDto;
import com.tousime_alternative.dto.UpdatePasswordDto;
import com.tousime_alternative.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/partner")
public class PartnerControlller {
    private PartnerService partnerService;

    @Autowired
    public PartnerControlller(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @GetMapping("/all")
    public List<PartnerDto> findAll() {
        return partnerService.findAll();
    }

    @PostMapping("/update")
    public ResponseEntity<PartnerDto> update(@RequestBody PartnerDto user) {
        return ResponseEntity.ok(partnerService.update(user));
    }

    @DeleteMapping("/delete/{idUser}")
    public void delete(@PathVariable("idUser") Long id) {
        partnerService.delete(id);
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<PartnerDto> updatePassword(@RequestBody UpdatePasswordDto dto) {
        return ResponseEntity.ok(partnerService.updatePassword(dto));
    }
}
