package com.moradiasestudantis.gestao_moradias.controller;

import com.moradiasestudantis.gestao_moradias.entity.Estudante;
import com.moradiasestudantis.gestao_moradias.service.EstudanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class EstudanteController {

    private final EstudanteService estudanteService;

    @GetMapping("/search")
    @PreAuthorize("hasRole('PROPRIETARIO')")
    public List<Estudante> buscarEstudantes(
            @RequestParam(required = false) String preferenciaMoradia,
            @RequestParam(required = false) Boolean aceitaPets,
            @RequestParam(required = false) Boolean precisaMobilidadeReduzida) {
        return estudanteService.buscarPorPreferencias(preferenciaMoradia, aceitaPets, precisaMobilidadeReduzida);
    }
}