package com.abhyasika.entity;

import java.time.LocalDate;
import java.util.List;

import com.abhyasika.enums.Gender;
import com.abhyasika.enums.StudentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    
    @OneToMany(mappedBy = "student")
    private List<Attendance> attendanceList;
    
    
    @OneToMany(mappedBy = "student")
    private List<Fees> feesList;
    
    @OneToMany(mappedBy = "student")
    private List<Complaint> complaintList;

    @OneToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;
}