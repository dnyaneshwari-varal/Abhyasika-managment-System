package com.abhyasika.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhyasika.entity.Fees;
import com.abhyasika.entity.Student;
import com.abhyasika.enums.FeeStatus;



public interface FeesRepository extends JpaRepository<Fees, Long> {

    List<Fees> findByStudent(Student student);

    List<Fees> findByFeeStatus(FeeStatus feeStatus);

}