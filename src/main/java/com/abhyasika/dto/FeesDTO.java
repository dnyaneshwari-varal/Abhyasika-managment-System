package com.abhyasika.dto;

import java.time.LocalDate;

import com.abhyasika.enums.PaymentMethod;
import com.abhyasika.enums.PaymentStatus;

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
public class FeesDTO {

    private String studentId;

    private String studentName;

    private String month;

    private int year;

    private Double totalFee;

    private Double paidAmount;

    private Double pendingAmount;

    private PaymentStatus paymentStatus;

    private LocalDate paymentDate;

    private PaymentMethod paymentMethod;

    private String transactionId;

    private String remarks;

}