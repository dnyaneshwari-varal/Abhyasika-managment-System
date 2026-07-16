package com.abhyasika.service;

import java.time.LocalDate;
import java.util.List;

import com.abhyasika.dto.AttendanceDTO;
import com.abhyasika.dto.AttendanceDashboardDTO;

public interface AttendanceService {

    // Admin marks attendance
    AttendanceDTO markAttendance(AttendanceDTO attendanceDTO);

    // Attendance history of one student
    List<AttendanceDTO> getAttendanceByStudent(String studentId);

    // Attendance of all students on a particular date
    List<AttendanceDTO> getAttendanceByDate(LocalDate attendanceDate);

    // Monthly attendance of a student
    List<AttendanceDTO> getMonthlyAttendance(String studentId, int year, int month);
    
    AttendanceDashboardDTO getTodayAttendanceDashboard();

}