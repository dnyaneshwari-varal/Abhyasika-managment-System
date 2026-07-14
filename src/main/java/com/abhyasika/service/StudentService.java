package com.abhyasika.service;

import java.util.List;
import com.abhyasika.dto.StudentDTO;

public interface StudentService {

	//StudentDTO return type
    //this method take data from StudentDTO and register that student in database.
	StudentDTO registerStudent(StudentDTO studentDTO);
   
    //it give list of registered student 
    List<StudentDTO> getAllStudents();
   
    //find student by id
    StudentDTO getStudentById(Long id);
    
  //updated student by id
    StudentDTO updateStudent(Long id, StudentDTO studentDTO);

    //delete student 
    void deleteStudent(String studentId);

}