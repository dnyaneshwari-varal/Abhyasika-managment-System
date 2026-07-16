package com.abhyasika.dto;

import java.time.LocalDate;

import com.abhyasika.enums.ComplaintCategory;
import com.abhyasika.enums.ComplaintStatus;

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
public class ComplaintDTO {

    private String studentId;

    private String studentName;
    
    private Long id;

    private ComplaintCategory complaintCategory;

    private String title;

    private String description;

    private LocalDate complaintDate;

    private ComplaintStatus complaintStatus;

}