package dev.ashish.EcomPaymentService.strategy;

import dev.ashish.EcomPaymentService.dto.PaymentRequestDTO;
import dev.ashish.EcomPaymentService.entity.Payment;

// Concrete strategy
public class StripPaymentLinkGenerator implements PaymentLinkGeneratorStrategy{
    @Override
    public PaymentLinkResponse generatePaymentLink(Payment payment) {
        return null;
    }
}