package com.abhyasika.dto;

import java.time.LocalDate;

import com.abhyasika.enums.AttendanceStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AttendanceDTO {

    private String studentId;

    private String studentName;

    private LocalDate attendanceDate;

    private AttendanceStatus attendanceStatus;
    
    private String roomCode;
    private String seatNumber;

}