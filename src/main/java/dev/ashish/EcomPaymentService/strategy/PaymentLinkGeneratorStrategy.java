package dev.ashish.EcomPaymentService.strategy;

import dev.ashish.EcomPaymentService.dto.PaymentRequestDTO;
import dev.ashish.EcomPaymentService.entity.Payment;

//Strategy Interface
public interface PaymentLinkGeneratorStrategy {
    PaymentLinkResponse generatePaymentLink(Payment payment);
}
