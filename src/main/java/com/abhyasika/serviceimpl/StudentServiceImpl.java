package com.abhyasika.serviceimpl;

import java.time.LocalDate;
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
		Student student = new Student();
		
		// copy data of studentDTO to entity because studentRepository needs entity not dto of student
		student.setFullName(studentDTO.getFullName());
		student.setMobile(studentDTO.getMobile());
		student.setEmail(studentDTO.getEmail());
		student.setGender(studentDTO.getGender());
		student.setAddress(studentDTO.getAddress());
		//When a student registers today, the backend automatically stores today's date.
		student.setJoinDate(LocalDate.now());
		//When a student registers successfully, we want their status to become ACTIVE.
	    student.setStatus(StudentStatus.ACTIVE);
		
	   // Save in Database
	    Student savedStudent = studentRepository.save(student);

	    // Convert Entity to DTO
	    StudentDTO response = new StudentDTO();

	    response.setFullName(savedStudent.getFullName());
	    response.setMobile(savedStudent.getMobile());
	    response.setEmail(savedStudent.getEmail());
	    response.setGender(savedStudent.getGender());
	    response.setAddress(savedStudent.getAddress());

	    return response;
	}

	@Override
	public List<StudentDTO> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentDTO getStudentById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStudent(Long id) {
		// TODO Auto-generated method stub
		
	}

}
