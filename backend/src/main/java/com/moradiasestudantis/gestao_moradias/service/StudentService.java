package com.moradiasestudantis.gestao_moradias.service;

import com.moradiasestudantis.gestao_moradias.dto.StudentDto;
import com.moradiasestudantis.gestao_moradias.dto.StudentFilterDto;
import com.moradiasestudantis.gestao_moradias.model.Student;
import com.moradiasestudantis.gestao_moradias.repository.StudentRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado com ID: " + id));
        return new StudentDto(student);
    }

    @Transactional
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado com ID: " + id));

        student.setNomeCompleto(studentDetails.getNomeCompleto());
        student.setCpf(studentDetails.getCpf());
        student.setDataNascimento(studentDetails.getDataNascimento());
        student.setTelefone(studentDetails.getTelefone());
        student.setCurso(studentDetails.getCurso());
        student.setPeriodoAtual(studentDetails.getPeriodoAtual());
        student.setWifi(studentDetails.isWifi());
        student.setGaragem(studentDetails.isGaragem());
        student.setMobiliado(studentDetails.isMobiliado());
        student.setBanheiroPrivativo(studentDetails.isBanheiroPrivativo());

        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<StudentDto> searchStudents(StudentFilterDto filter) {
        Specification<Student> spec = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getWifi()) {
                predicates.add(builder.equal(root.get("wifi"), filter.getWifi()));
            }
            if (filter.getGaragem() != null) {
                predicates.add(builder.equal(root.get("garagem"), filter.getGaragem()));
            }
            if (filter.getBanheiroPrivativo() != null) {
                predicates.add(builder.equal(root.get("banheiroPrivativo"), filter.getBanheiroPrivativo()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return studentRepository.findAll(spec).stream()
                .map(StudentDto::new)
                .collect(Collectors.toList());
    }
}