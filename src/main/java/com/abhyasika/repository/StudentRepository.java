package com.abhyasika.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhyasika.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
