package dev.ashish.EcomPaymentService.service;

import dev.ashish.EcomPaymentService.dto.PaymentRequestDTO;
import dev.ashish.EcomPaymentService.dto.PaymentResponseDTO;

public interface PaymentService {
    PaymentResponseDTO createPayment(PaymentRequestDTO requestDTO);

}
