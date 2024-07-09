package dev.ashish.EcomPaymentService.strategy;

public interface PaymentLinkResponse {
    String getPaymentLink();
    String getPaymentId();
    String getCurrency();
    double getAmount();
    String getDescription();
}
