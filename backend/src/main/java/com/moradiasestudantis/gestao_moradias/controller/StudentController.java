package com.moradiasestudantis.gestao_moradias.controller;

import com.moradiasestudantis.gestao_moradias.dto.StudentDto;
import com.moradiasestudantis.gestao_moradias.dto.StudentFilterDto;
import com.moradiasestudantis.gestao_moradias.model.Student;
import com.moradiasestudantis.gestao_moradias.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        StudentDto student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        return ResponseEntity.ok(studentService.updateStudent(id, studentDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentDto>> searchStudents(
            @ModelAttribute StudentFilterDto filter) {
        List<StudentDto> students = studentService.searchStudents(filter);
        return ResponseEntity.ok(students);
    }
}