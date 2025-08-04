package com.moradiasestudantis.gestao_moradias.controller;

import com.moradiasestudantis.gestao_moradias.dto.ResidenceDto;
import com.moradiasestudantis.gestao_moradias.model.Residence;
import com.moradiasestudantis.gestao_moradias.service.ResidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residences")
public class ResidenceController {

    @Autowired
    private ResidenceService residenceService;

    @GetMapping("/filter")
    public ResponseEntity<List<Residence>> filterResidences(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String finalidade) {
        List<Residence> residences = residenceService.filterResidences(tipo, finalidade);
        return ResponseEntity.ok(residences);
    }

    @GetMapping
    public ResponseEntity<List<Residence>> getAllResidences() {
        return ResponseEntity.ok(residenceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResidenceDto> getResidenceById(@PathVariable Long id) {
        Residence residence = residenceService.findById(id);
        ResidenceDto dto = new ResidenceDto(residence);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Residence> createResidence(@RequestBody Residence residence) {
        return ResponseEntity.ok(residenceService.save(residence));
    }
}
