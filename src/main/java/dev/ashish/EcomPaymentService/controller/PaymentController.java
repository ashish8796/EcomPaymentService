package dev.ashish.EcomPaymentService.controller;

import dev.ashish.EcomPaymentService.dto.PaymentRequestDTO;
import dev.ashish.EcomPaymentService.dto.PaymentResponseDTO;
import dev.ashish.EcomPaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public ResponseEntity createPayment(@RequestBody PaymentRequestDTO requestDTO) {
        PaymentResponseDTO responseDTO = paymentService.createPayment(requestDTO);

        return ResponseEntity.ok(responseDTO);
    }
}
