package dev.ashish.EcomPaymentService.strategy;

public class RazorpayPaymentLinkResponse implements PaymentLinkResponse{
    private String paymentLink;
    private String paymentId;
    private String currency;
    private long amount;
    private String description;

    public RazorpayPaymentLinkResponse(String paymentLink, String paymentId, String currency, long amount, String description) {
        this.paymentLink = paymentLink;
        this.paymentId = paymentId;
        this.currency = currency;
        this.amount = amount;
        this.description = description;
    }

    @Override
    public String getPaymentLink() {
        return paymentLink;
    }

    @Override
    public String getPaymentId() {
        return paymentId;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public Long getAmount() {
        return amount;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
