package com.moradiasestudantis.gestao_moradias.service;

import com.moradiasestudantis.gestao_moradias.dto.ResidenceDto;
import com.moradiasestudantis.gestao_moradias.model.Residence;
import com.moradiasestudantis.gestao_moradias.model.User;
import com.moradiasestudantis.gestao_moradias.repository.ResidenceRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResidenceService {

    @Autowired
    private ResidenceRepository residenceRepository;

    @Transactional
    public ResidenceDto createResidence(ResidenceDto dto, User owner) {
        Residence residence = new Residence();
        residence.setAddress(dto.address());
        residence.setDescription(dto.description());
        residence.setPrice(dto.price());
        residence.setOwner(owner);

        Residence savedResidence = residenceRepository.save(residence);
        return new ResidenceDto(savedResidence);
    }

    @Transactional(readOnly = true)
    public ResidenceDto getResidenceById(Long id) {
        Residence residence = residenceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Residência não encontrada"));
        return new ResidenceDto(residence);
    }

    @Transactional(readOnly = true)
    public List<ResidenceDto> findResidencesByOwner(User owner) {
        return residenceRepository.findByOwner(owner)
                .stream()
                .map(ResidenceDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ResidenceDto updateResidence(Long id, ResidenceDto dto, User currentUser) {
        Residence residence = residenceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Residência não encontrada"));

        if (!residence.getOwner().getId().equals(currentUser.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado");
        }

        residence.setAddress(dto.address());
        residence.setDescription(dto.description());
        residence.setPrice(dto.price());

        Residence updatedResidence = residenceRepository.save(residence);
        return new ResidenceDto(updatedResidence);
    }

    @Transactional
    public void deleteResidence(Long id, User currentUser) {
        Residence residence = residenceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Residência não encontrada"));

        if (!residence.getOwner().getId().equals(currentUser.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado");
        }

        residenceRepository.delete(residence);
    }

    @Transactional(readOnly = true)
    public List<ResidenceDto> searchResidences(String tipo, String finalidade) {
        Specification<Residence> spec = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // Nota: Os campos 'tipo' e 'finalidade' foram removidos do modelo 'Residence'.
            // Você precisará adicioná-los de volta ao 'Residence.java' e 'ResidenceDto.java' se quiser usar este filtro.
            // Exemplo de como seria a lógica:
            // if (tipo != null && !tipo.isBlank()) {
            //     predicates.add(builder.equal(root.get("tipo"), tipo));
            // }
            // if (finalidade != null && !finalidade.isBlank()) {
            //     predicates.add(builder.equal(root.get("finalidade"), finalidade));
            // }
            return builder.and(predicates.toArray(new Predicate[0]));
        };

        return residenceRepository.findAll(spec).stream()
                .map(ResidenceDto::new)
                .collect(Collectors.toList());
    }
}