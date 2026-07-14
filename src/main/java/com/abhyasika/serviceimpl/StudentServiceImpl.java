package com.abhyasika.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.abhyasika.dto.StudentDTO;
import com.abhyasika.entity.Student;
import com.abhyasika.enums.StudentStatus;
import com.abhyasika.repository.StudentRepository;
import com.abhyasika.service.StudentService;


@Service
public class StudentServiceImpl implements StudentService{
	
	private final StudentRepository studentRepository;
	public StudentServiceImpl(StudentRepository studentRepository) {
	        this.studentRepository = studentRepository;
	}
	
	

	@Override
	public StudentDTO registerStudent(StudentDTO studentDTO) {
		if (studentRepository.existsByEmail(studentDTO.getEmail())) {
		    throw new RuntimeException("Email already exists");
		}

		if (studentRepository.existsByMobile(studentDTO.getMobile())) {
		    throw new RuntimeException("Mobile number already exists");
		}
		Student student = new Student();
		
		// copy data of studentDTO to entity because studentRepository needs entity not dto of student
		
		student.setFullName(studentDTO.getFullName());
		student.setMobile(studentDTO.getMobile());
		student.setEmail(studentDTO.getEmail());
		student.setGender(studentDTO.getGender());
		student.setPassword(studentDTO.getPassword());
		student.setAddress(studentDTO.getAddress());
		//When a student registers today, the backend automatically stores today's date.
		student.setJoinDate(LocalDate.now());
		//When a student registers successfully, we want their status to become ACTIVE.
	    student.setStatus(StudentStatus.ACTIVE);
		
	   // Save in Database
	    Student savedStudent = studentRepository.save(student);
	    savedStudent.setStudentId(String.format("STU%03d", savedStudent.getId()));

	    studentRepository.save(savedStudent);

	    // Convert Entity to DTO
	    StudentDTO response = new StudentDTO();
	    response.setStudentId(savedStudent.getStudentId());
	    response.setFullName(savedStudent.getFullName());
	    response.setMobile(savedStudent.getMobile());
	    response.setEmail(savedStudent.getEmail());
	    response.setGender(savedStudent.getGender());
	    response.setAddress(savedStudent.getAddress());

	    return response;
	}

	
	@Override
	public List<StudentDTO> getAllStudents() {

	    List<Student> students = studentRepository.findAll();

	    List<StudentDTO> studentDTOList = new ArrayList<>();

	    for (Student student : students) {
	    	

	        StudentDTO dto = new StudentDTO();
	        
	        dto.setStudentId(student.getStudentId());
	        dto.setFullName(student.getFullName());
	        dto.setMobile(student.getMobile());
	        dto.setEmail(student.getEmail());
	        dto.setGender(student.getGender());
	        dto.setAddress(student.getAddress());

	        studentDTOList.add(dto);
	    }

	    return studentDTOList;
	}

	@Override
	public StudentDTO getStudentById(Long id) {

	    Student student = studentRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Student not found"));

	    StudentDTO dto = new StudentDTO();
	    dto.setStudentId(student.getStudentId());
	    dto.setFullName(student.getFullName());
	    dto.setMobile(student.getMobile());
	    dto.setEmail(student.getEmail());
	    dto.setGender(student.getGender());
	    dto.setAddress(student.getAddress());

	    return dto;
	}
	@Override
	public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {

	    Student student = studentRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Student not found"));

	    // Update values
	    
	    student.setFullName(studentDTO.getFullName());
	    student.setMobile(studentDTO.getMobile());
	    student.setEmail(studentDTO.getEmail());
	    student.setGender(studentDTO.getGender());
	    student.setAddress(studentDTO.getAddress());

	    Student updatedStudent = studentRepository.save(student);

	    StudentDTO response = new StudentDTO();
	    response.setStudentId(updatedStudent.getStudentId());
	    response.setFullName(updatedStudent.getFullName());
	    response.setMobile(updatedStudent.getMobile());
	    response.setEmail(updatedStudent.getEmail());
	    response.setGender(updatedStudent.getGender());
	    response.setAddress(updatedStudent.getAddress());

	    return response;
	}

	@Override
	public void deleteStudent(String studentId) {

		Student student = studentRepository.findByStudentId(studentId)
		        .orElseThrow(() -> new RuntimeException("Student not found"));

		studentRepository.delete(student);

	}
	
	

}
