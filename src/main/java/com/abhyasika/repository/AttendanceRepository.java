package com.abhyasika.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhyasika.entity.Attendance;
import com.abhyasika.entity.Student;
import com.abhyasika.enums.AttendanceStatus;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // Prevent duplicate attendance
    boolean existsByStudentAndAttendanceDate(Student student, LocalDate attendanceDate);

    // Student attendance history
    List<Attendance> findByStudent(Student student);

    // Attendance for a particular day
    List<Attendance> findByAttendanceDate(LocalDate attendanceDate);

    // Monthly attendance
    List<Attendance> findByStudentAndAttendanceDateBetween(
            Student student,
            LocalDate startDate,
            LocalDate endDate
    );
    
    
    long countByAttendanceDate(LocalDate attendanceDate);

    long countByAttendanceDateAndAttendanceStatus(
            LocalDate attendanceDate,
            AttendanceStatus attendanceStatus
    );
    
    
}