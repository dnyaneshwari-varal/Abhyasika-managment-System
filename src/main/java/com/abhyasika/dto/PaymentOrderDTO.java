package com.abhyasika.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOrderDTO {

    private Long feeId;
    private String orderId;
    private Integer amount;
    private String currency;
    private String key;
}