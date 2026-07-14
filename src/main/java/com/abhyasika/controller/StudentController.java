package com.abhyasika.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhyasika.dto.StudentDTO;
import com.abhyasika.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/register")
   
    public ResponseEntity<StudentDTO> registerStudent(@Valid @RequestBody StudentDTO studentDTO){

        StudentDTO savedStudent = studentService.registerStudent(studentDTO);

        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {

        List<StudentDTO> students = studentService.getAllStudents();

        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {

        StudentDTO student = studentService.getStudentById(id);

        return ResponseEntity.ok(student);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentDTO studentDTO) {

        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);

        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable String studentId) {

        studentService.deleteStudent(studentId);

        return ResponseEntity.ok("Student Deleted Successfully");

    }
}
