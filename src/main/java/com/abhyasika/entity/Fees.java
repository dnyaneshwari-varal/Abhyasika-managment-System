package com.abhyasika.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.abhyasika.enums.FeeStatus;
import com.abhyasika.enums.MembershipType;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fees")
public class Fees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    
    
    @Enumerated(EnumType.STRING)
    private MembershipType membershipType;

    private Integer durationInMonths;

    private BigDecimal monthlyFee;

    private BigDecimal originalAmount;
    
    private LocalDate membershipStartDate;

    private LocalDate membershipEndDate;
    
    private LocalDate dueDate;

    private Double discountPercentage;

    private BigDecimal discountAmount;

    private BigDecimal finalAmount;

    @Enumerated(EnumType.STRING)
    private FeeStatus feeStatus;

    private LocalDate paymentDate;
}