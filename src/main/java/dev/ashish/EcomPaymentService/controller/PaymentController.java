package dev.ashish.EcomPaymentService.controller;

import dev.ashish.EcomPaymentService.dto.PaymentRequestDTO;
import dev.ashish.EcomPaymentService.dto.PaymentResponseDTO;
import dev.ashish.EcomPaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/welcome")
    public ResponseEntity welcomePayment() {
        return ResponseEntity.ok("Welcome to Ecom Payment Service.");
    }

    @PostMapping("/pay")
    public ResponseEntity createPayment(@RequestBody PaymentRequestDTO requestDTO) {
        System.out.println(requestDTO.toString());
        PaymentResponseDTO responseDTO = paymentService.createPayment(requestDTO);

        return ResponseEntity.ok(responseDTO);
    }
}
