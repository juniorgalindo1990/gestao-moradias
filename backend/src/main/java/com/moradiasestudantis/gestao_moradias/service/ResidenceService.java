package com.moradiasestudantis.gestao_moradias.service;

import com.moradiasestudantis.gestao_moradias.dto.ResidenceDto;
import com.moradiasestudantis.gestao_moradias.model.Residence;
import com.moradiasestudantis.gestao_moradias.model.User;
import com.moradiasestudantis.gestao_moradias.repository.ResidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResidenceService {

    @Autowired
    private ResidenceRepository residenceRepository;

    public ResidenceDto createResidence(ResidenceDto dto, User owner) {
        Residence residence = new Residence();
        residence.setAddress(dto.address());
        residence.setDescription(dto.description());
        residence.setPrice(dto.price());
        residence.setOwner(owner);

        Residence savedResidence = residenceRepository.save(residence);
        return new ResidenceDto(savedResidence);
    }

    public ResidenceDto getResidenceById(Long id) {
        Residence residence = residenceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Residência não encontrada"));
        return new ResidenceDto(residence);
    }

    public List<ResidenceDto> findResidencesByOwner(User owner) {
        return residenceRepository.findByOwner(owner)
                .stream()
                .map(ResidenceDto::new)
                .collect(Collectors.toList());
    }

    public ResidenceDto updateResidence(Long id, ResidenceDto dto, User currentUser) {
        Residence residence = residenceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Residência não encontrada"));

        // Garante que apenas o proprietário pode editar
        if (!residence.getOwner().getId().equals(currentUser.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado");
        }

        residence.setAddress(dto.address());
        residence.setDescription(dto.description());
        residence.setPrice(dto.price());

        Residence updatedResidence = residenceRepository.save(residence);
        return new ResidenceDto(updatedResidence);
    }

    public void deleteResidence(Long id, User currentUser) {
        Residence residence = residenceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Residência não encontrada"));

        // Garante que apenas o proprietário pode deletar
        if (!residence.getOwner().getId().equals(currentUser.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado");
        }

        residenceRepository.delete(residence);
    }
}
