package com.moradiasestudantis.gestao_moradias.controller;

import com.moradiasestudantis.gestao_moradias.dto.ResidenceDto;
import com.moradiasestudantis.gestao_moradias.entity.Residencia;
import com.moradiasestudantis.gestao_moradias.model.User;
import com.moradiasestudantis.gestao_moradias.service.ResidenceService;
import com.moradiasestudantis.gestao_moradias.service.ResidenciaService;
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

    @Autowired
    private ResidenciaService residenciaService;

    // Criar nova residência
    @PostMapping
    public ResponseEntity<ResidenceDto> create(@RequestBody @Valid ResidenceDto dto,
                                               @AuthenticationPrincipal User user) {
        ResidenceDto createdResidence = residenceService.createResidence(dto, user);
        return ResponseEntity.status(201).body(createdResidence);
    }

    // Buscar todas as residências do usuário
    @GetMapping
    public ResponseEntity<List<ResidenceDto>> getAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(residenceService.findResidencesByOwner(user));
    }

    // Filtro por tipo ou finalidade
    @GetMapping("/search")
    public List<Residencia> buscarResidencias(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String finalidade) {
        return residenciaService.buscarPorFiltro(tipo, finalidade);
    }
}
