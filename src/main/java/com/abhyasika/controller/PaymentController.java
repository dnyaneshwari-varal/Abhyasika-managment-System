package com.abhyasika.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhyasika.dto.PaymentOrderDTO;
import com.abhyasika.dto.PaymentVerifyDTO;
import com.abhyasika.entity.Fees;
import com.abhyasika.repository.FeesRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    private final RazorpayClient razorpayClient;
    private final FeesRepository feesRepository;

    @Value("${razorpay.key.id}")
    private String razorpayKey;

    public PaymentController(RazorpayClient razorpayClient,
                             FeesRepository feesRepository) {
        this.razorpayClient = razorpayClient;
        this.feesRepository = feesRepository;
    }

    @PostMapping("/create-order/{feeId}")
    public PaymentOrderDTO createOrder(@PathVariable Long feeId) throws Exception {

        Fees fees = feesRepository.findById(feeId)
                .orElseThrow(() -> new RuntimeException("Fees not found"));

        JSONObject orderRequest = new JSONObject();

        orderRequest.put("amount", fees.getFinalAmount().multiply(java.math.BigDecimal.valueOf(100)).intValue());
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "fee_" + feeId);

        Order order = razorpayClient.orders.create(orderRequest);

        PaymentOrderDTO dto = new PaymentOrderDTO();

        dto.setFeeId(feeId);
        dto.setOrderId(order.get("id"));
        dto.setAmount(order.get("amount"));
        dto.setCurrency(order.get("currency"));
        dto.setKey(razorpayKey);

        return dto;
    }
    
    @PostMapping("/verify")
    public String verifyPayment(@RequestBody PaymentVerifyDTO paymentDTO) {

        Fees fees = feesRepository.findById(paymentDTO.getFeeId())
                .orElseThrow(() -> new RuntimeException("Fees record not found"));

        fees.setFeeStatus(com.abhyasika.enums.FeeStatus.PAID);
        fees.setPaymentDate(java.time.LocalDate.now());

        feesRepository.save(fees);

        return "Payment verified successfully. Fees paid.";
    }
}