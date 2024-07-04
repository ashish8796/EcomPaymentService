package dev.ashish.EcomPaymentService.strategy;

// Context class
public class PaymentLinkGenerator {
    private PaymentLinkGeneratorStrategy generatorStrategy;

    public PaymentLinkGenerator(PaymentLinkGeneratorStrategy generatorStrategy) {
        this.generatorStrategy = generatorStrategy;
    }

    public PaymentLinkResponse generatePaymentLink() {
       return generatorStrategy.generatePaymentLink();
    }
}
