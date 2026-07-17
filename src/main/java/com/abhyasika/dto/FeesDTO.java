package com.abhyasika.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.abhyasika.enums.FeeStatus;
import com.abhyasika.enums.MembershipType;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeesDTO {

    private Long id;

    @NotNull(message = "Student Id is required")
    private Long studentId;

    @NotNull(message = "Membership Type is required")
    private MembershipType membershipType;

    private Integer durationInMonths;

    private BigDecimal monthlyFee;

    private BigDecimal originalAmount;

    private Double discountPercentage;

    private BigDecimal discountAmount;

    private BigDecimal finalAmount;

    private LocalDate membershipStartDate;

    private LocalDate membershipEndDate;

    private LocalDate dueDate;

    private FeeStatus feeStatus;

    private LocalDate paymentDate;
}