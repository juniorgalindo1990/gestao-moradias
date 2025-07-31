package com.moradiasestudantis.gestao_moradias.controller;

import com.moradiasestudantis.gestao_moradias.dto.ResidenceDto;
import com.moradiasestudantis.gestao_moradias.model.User;
import com.moradiasestudantis.gestao_moradias.service.ResidenceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residences")
public class ResidenceController {

    @Autowired
    private ResidenceService residenceService;

    @PostMapping
    public ResponseEntity<ResidenceDto> create(@RequestBody @Valid ResidenceDto dto,
                                               @AuthenticationPrincipal User user) {
        ResidenceDto createdResidence = residenceService.createResidence(dto, user);
        return ResponseEntity.status(201).body(createdResidence);
    }

    @GetMapping
    public ResponseEntity<List<ResidenceDto>> getAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(residenceService.findResidencesByOwner(user));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ResidenceDto>> searchResidences(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String finalidade) {
        List<ResidenceDto> residences = residenceService.searchResidences(tipo, finalidade);
        return ResponseEntity.ok(residences);
    }
}