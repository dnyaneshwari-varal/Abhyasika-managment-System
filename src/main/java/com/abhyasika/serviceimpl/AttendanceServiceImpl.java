package com.abhyasika.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhyasika.dto.AttendanceDTO;
import com.abhyasika.dto.AttendanceDashboardDTO;
import com.abhyasika.entity.Attendance;
import com.abhyasika.entity.Student;
import com.abhyasika.enums.AttendanceStatus;
import com.abhyasika.enums.StudentStatus;
import com.abhyasika.repository.AttendanceRepository;
import com.abhyasika.repository.StudentRepository;
import com.abhyasika.service.AttendanceService;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository,
                                 StudentRepository studentRepository) {

        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
    }


    @Override
    public AttendanceDTO markAttendance(AttendanceDTO attendanceDTO) {

        Student student = studentRepository.findByStudentId(attendanceDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        LocalDate attendanceDate = attendanceDTO.getAttendanceDate();

        if (attendanceDate == null) {
            attendanceDate = LocalDate.now();
            
            
        }

        if (attendanceDate.isAfter(LocalDate.now())) {
            throw new RuntimeException("Attendance cannot be marked for a future date.");
        }
        
        if (attendanceRepository.existsByStudentAndAttendanceDate(student, attendanceDate)) {
            throw new RuntimeException("Attendance already marked for this date");
        }

        if (student.getStatus() != StudentStatus.ACTIVE) {
            throw new RuntimeException("Only active students can be marked for attendance.");
        }

        Attendance attendance = new Attendance();

        attendance.setAttendanceDate(attendanceDate);
        attendance.setAttendanceStatus(attendanceDTO.getAttendanceStatus());
        attendance.setStudent(student);

        Attendance savedAttendance = attendanceRepository.save(attendance);

        AttendanceDTO response = new AttendanceDTO();

        response.setStudentId(student.getStudentId());
        response.setStudentName(student.getFullName());

        if (student.getSeat() != null) {
            response.setRoomCode(student.getSeat().getRoom().getRoomCode());
            response.setSeatNumber(student.getSeat().getSeatNumber());
        }

        response.setAttendanceDate(savedAttendance.getAttendanceDate());
        response.setAttendanceStatus(savedAttendance.getAttendanceStatus());

        return response;
    }
	
    @Override
    public List<AttendanceDTO> getAttendanceByStudent(String studentId) {

        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Attendance> attendanceList = attendanceRepository.findByStudent(student);

        List<AttendanceDTO> response = new ArrayList<>();

        for (Attendance attendance : attendanceList) {

            AttendanceDTO dto = new AttendanceDTO();

            dto.setStudentId(student.getStudentId());
            dto.setStudentName(student.getFullName());
            dto.setRoomCode(student.getSeat().getRoom().getRoomCode());
            dto.setSeatNumber(student.getSeat().getSeatNumber());
            dto.setAttendanceDate(attendance.getAttendanceDate());
            dto.setAttendanceStatus(attendance.getAttendanceStatus());

            response.add(dto);
        }

        return response;
    }

    @Override
    public List<AttendanceDTO> getAttendanceByDate(LocalDate attendanceDate) {

        List<Attendance> attendanceList =
                attendanceRepository.findByAttendanceDate(attendanceDate);

        List<AttendanceDTO> response = new ArrayList<>();

        for (Attendance attendance : attendanceList) {

            AttendanceDTO dto = new AttendanceDTO();

            dto.setStudentId(attendance.getStudent().getStudentId());
            dto.setStudentName(attendance.getStudent().getFullName());
            dto.setRoomCode(attendance.getStudent().getSeat().getRoom().getRoomCode());
            dto.setSeatNumber(attendance.getStudent().getSeat().getSeatNumber());
            dto.setAttendanceDate(attendance.getAttendanceDate());
            dto.setAttendanceStatus(attendance.getAttendanceStatus());

            response.add(dto);
        }

        return response;
    }

    @Override
    public List<AttendanceDTO> getMonthlyAttendance(String studentId, int year, int month) {

        // Find Student
        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // First day of month
        LocalDate startDate = LocalDate.of(year, month, 1);

        // Last day of month
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        // Get attendance between dates
        List<Attendance> attendanceList = attendanceRepository
                .findByStudentAndAttendanceDateBetween(student, startDate, endDate);

        List<AttendanceDTO> response = new ArrayList<>();

        for (Attendance attendance : attendanceList) {

            AttendanceDTO dto = new AttendanceDTO();

            dto.setStudentId(student.getStudentId());
            dto.setStudentName(student.getFullName());

            if (student.getSeat() != null) {
                dto.setRoomCode(student.getSeat().getRoom().getRoomCode());
                dto.setSeatNumber(student.getSeat().getSeatNumber());
            }

            dto.setAttendanceDate(attendance.getAttendanceDate());
            dto.setAttendanceStatus(attendance.getAttendanceStatus());

            response.add(dto);
        }

        return response;
    }
    
    @Override
    public AttendanceDashboardDTO getTodayAttendanceDashboard() {

        LocalDate today = LocalDate.now();

        long totalStudents = studentRepository.count();

        long presentStudents = attendanceRepository
                .countByAttendanceDateAndAttendanceStatus(today, AttendanceStatus.PRESENT);

        long absentStudents = totalStudents - presentStudents;

        double attendancePercentage = 0;

        if (totalStudents > 0) {
            attendancePercentage =
                    (presentStudents * 100.0) / totalStudents;
        }

        AttendanceDashboardDTO dashboard = new AttendanceDashboardDTO();

        dashboard.setTotalStudents(totalStudents);
        dashboard.setPresentStudents(presentStudents);
        dashboard.setAbsentStudents(absentStudents);
        dashboard.setAttendancePercentage(attendancePercentage);

        return dashboard;
    }

}