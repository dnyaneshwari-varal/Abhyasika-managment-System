package com.abhyasika.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentVerifyDTO {

    private Long feeId;

    private String razorpayOrderId;

    private String razorpayPaymentId;

    private String razorpaySignature;
}