package dev.ashish.EcomPaymentService.strategy;

import dev.ashish.EcomPaymentService.entity.Payment;

// Context class
public class PaymentLinkGenerator {
    private PaymentLinkGeneratorStrategy generatorStrategy;

    public PaymentLinkGenerator(PaymentLinkGeneratorStrategy generatorStrategy) {
        this.generatorStrategy = generatorStrategy;
    }

    public PaymentLinkResponse generatePaymentLink(Payment payment) {
       return generatorStrategy.generatePaymentLink(payment);
    }
}
