package com.abhyasika.entity;

import java.time.LocalDate;

import com.abhyasika.enums.AttendanceStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate attendanceDate;

	@Enumerated(EnumType.STRING)
	private AttendanceStatus attendanceStatus;

}
