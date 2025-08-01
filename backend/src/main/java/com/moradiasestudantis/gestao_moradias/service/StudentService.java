package com.moradiasestudantis.gestao_moradias.service;

import com.moradiasestudantis.gestao_moradias.dto.StudentDto;
import com.moradiasestudantis.gestao_moradias.dto.StudentFilterDto;
import com.moradiasestudantis.gestao_moradias.model.Student;
import com.moradiasestudantis.gestao_moradias.model.User;
import com.moradiasestudantis.gestao_moradias.repository.StudentRepository;
import com.moradiasestudantis.gestao_moradias.repository.UserRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public StudentDto getProfile(UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        Student student = studentRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Perfil de estudante não encontrado"));

        return new StudentDto(student);
    }

    @Transactional
    public StudentDto createOrUpdateProfile(StudentDto profileDto, UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        Student student = studentRepository.findByUserId(user.getId())
                .orElse(new Student());

        student.setUser(user);
        student.setNomeCompleto(profileDto.nomeCompleto());
        student.setCpf(profileDto.cpf());
        student.setDataNascimento(profileDto.dataNascimento());
        student.setTelefone(profileDto.telefone());
        student.setPeriodoAtual(profileDto.periodoAtual());
        student.setCurso(profileDto.curso());
        student.setWifi(profileDto.wifi());
        student.setGaragem(profileDto.garagem());
        student.setMobiliado(profileDto.mobiliado());
        student.setBanheiroPrivativo(profileDto.banheiroPrivativo());

        studentRepository.save(student);

        return new StudentDto(student);
    }

    @Transactional(readOnly = true)
    public List<StudentDto> searchStudents(StudentFilterDto filter) {
        Specification<Student> spec = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getWifi() != true) {
                predicates.add(builder.equal(root.get("wifi"), filter.getWifi()));
            }
            if (filter.getGaragem() != null) {
                predicates.add(builder.equal(root.get("garagem"), filter.getGaragem()));
            }
            if (filter.getbanheiroPrivativo() != null) {
                predicates.add(builder.equal(root.get("banheiroPrivativo"), filter.getbanheiroPrivativo()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
        List<StudentDto> students = studentRepository.findAll(spec).stream().map(StudentDto::new).collect(Collectors.toList());
        return students;
    }
}