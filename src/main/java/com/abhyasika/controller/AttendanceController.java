package com.abhyasika.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhyasika.dto.AttendanceDTO;
import com.abhyasika.dto.AttendanceDashboardDTO;
import com.abhyasika.service.AttendanceService;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public ResponseEntity<AttendanceDTO> markAttendance(@RequestBody AttendanceDTO attendanceDTO) {

        AttendanceDTO response = attendanceService.markAttendance(attendanceDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceByStudent(
            @PathVariable String studentId) {

        List<AttendanceDTO> response =
                attendanceService.getAttendanceByStudent(studentId);

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/date/{attendanceDate}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceByDate(
            @PathVariable LocalDate attendanceDate) {

        List<AttendanceDTO> response =
                attendanceService.getAttendanceByDate(attendanceDate);

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/student/{studentId}/month/{year}/{month}")
    public ResponseEntity<List<AttendanceDTO>> getMonthlyAttendance(
            @PathVariable String studentId,
            @PathVariable int year,
            @PathVariable int month) {

        List<AttendanceDTO> response =
                attendanceService.getMonthlyAttendance(studentId, year, month);

        return ResponseEntity.ok(response);
    }
    
    
    @GetMapping("/dashboard/today")
    public ResponseEntity<AttendanceDashboardDTO> getTodayAttendanceDashboard() {

        AttendanceDashboardDTO response =
                attendanceService.getTodayAttendanceDashboard();

        return ResponseEntity.ok(response);
    }
}