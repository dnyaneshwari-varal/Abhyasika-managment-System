package com.abhyasika.entity;

import java.time.LocalDate;

import com.abhyasika.enums.ComplaintCategory;
import com.abhyasika.enums.ComplaintStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "complaints")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ComplaintCategory complaintCategory;

    private String title;

    private String description;

    private LocalDate complaintDate;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus complaintStatus;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

}