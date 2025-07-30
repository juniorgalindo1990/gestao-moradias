package com.moradiasestudantis.gestao_moradias.controller;

import com.moradiasestudantis.gestao_moradias.entity.Residencia;
import com.moradiasestudantis.gestao_moradias.service.ResidenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residences")
@RequiredArgsConstructor
public class ResidenciaController {

    private final ResidenciaService residenciaService;

    @GetMapping("/search")
    public List<Residencia> buscarResidencias(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String finalidade) {
        return residenciaService.buscarPorFiltro(tipo, finalidade);
    }
}