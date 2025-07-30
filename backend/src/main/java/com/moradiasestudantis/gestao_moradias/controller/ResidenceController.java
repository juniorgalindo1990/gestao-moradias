package com.moradiasestudantis.gestao_moradias.controller;

import com.moradiasestudantis.gestao_moradias.dto.ResidenceDto;
import com.moradiasestudantis.gestao_moradias.model.User;
import com.moradiasestudantis.gestao_moradias.service.ResidenceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residences")
public class ResidenceController {

    @Autowired
    private ResidenceService residenceService;

    // Tarefa 3.1 & 3.2: Criar uma nova residência
    @PostMapping
    public ResponseEntity<ResidenceDto> create(@RequestBody @Valid ResidenceDto dto, @AuthenticationPrincipal User user) {
        ResidenceDto createdResidence = residenceService.createResidence(dto, user);
        return new ResponseEntity<>(createdResidence, HttpStatus.CREATED);
    }

    // Tarefa 3.1: Obter uma residência por ID (público)
    @GetMapping("/{id}")
    public ResponseEntity<ResidenceDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(residenceService.getResidenceById(id));
    }

    // Tarefa 3.3: Listar as residências do proprietário logado
    @GetMapping("/my-residences")
    public ResponseEntity<List<ResidenceDto>> getMyResidences(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(residenceService.findResidencesByOwner(user));
    }

    // Tarefa 3.1 & 3.2: Atualizar uma residência
    @PutMapping("/{id}")
    public ResponseEntity<ResidenceDto> update(@PathVariable Long id, @RequestBody @Valid ResidenceDto dto, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(residenceService.updateResidence(id, dto, user));
    }

    // Tarefa 3.1 & 3.2: Deletar uma residência
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal User user) {
        residenceService.deleteResidence(id, user);
        return ResponseEntity.noContent().build();
    }
}
