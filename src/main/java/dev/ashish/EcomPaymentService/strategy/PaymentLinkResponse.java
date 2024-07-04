package dev.ashish.EcomPaymentService.strategy;

public interface PaymentLinkResponse {
    String getPaymentLink();
    String getPaymentId();
    String getCurrency();
    Long getAmount();
    String getDescription();
}
