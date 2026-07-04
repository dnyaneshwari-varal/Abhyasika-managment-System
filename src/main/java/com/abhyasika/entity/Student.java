package com.abhyasika.entity;

import java.time.LocalDate;

import com.abhyasika.enums.Gender;
import com.abhyasika.enums.StudentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String mobile;

    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String address;

    private LocalDate joinDate;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;


}