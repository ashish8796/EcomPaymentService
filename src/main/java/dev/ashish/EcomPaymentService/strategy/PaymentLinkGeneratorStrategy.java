package dev.ashish.EcomPaymentService.strategy;

//Strategy Interface
public interface PaymentLinkGeneratorStrategy {
    PaymentLinkResponse generatePaymentLink();
}
