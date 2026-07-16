package com.abhyasika.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhyasika.entity.Fees;
import com.abhyasika.entity.Student;
import com.abhyasika.enums.PaymentStatus;

public interface FeesRepository extends JpaRepository<Fees, Long> {

    List<Fees> findByStudent(Student student);

    List<Fees> findByPaymentStatus(PaymentStatus paymentStatus);

    Optional<Fees> findByStudentAndMonthAndYear(
            Student student,
            String month,
            int year
    );

}