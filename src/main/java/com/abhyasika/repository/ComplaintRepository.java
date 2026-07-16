package com.abhyasika.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhyasika.entity.Complaint;
import com.abhyasika.entity.Student;
import com.abhyasika.enums.ComplaintCategory;
import com.abhyasika.enums.ComplaintStatus;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByStudent(Student student);

    List<Complaint> findByComplaintStatus(ComplaintStatus complaintStatus);

    List<Complaint> findByComplaintCategory(ComplaintCategory complaintCategory);

    List<Complaint> findByStudentAndComplaintStatus(
            Student student,
            ComplaintStatus complaintStatus
    );
    
    long countByComplaintStatus(ComplaintStatus complaintStatus);

}