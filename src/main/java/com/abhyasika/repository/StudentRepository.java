package com.abhyasika.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhyasika.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
	Optional<Student> findByStudentId(String studentId);
	boolean existsByEmail(String email);

	boolean existsByMobile(String mobile);
}
