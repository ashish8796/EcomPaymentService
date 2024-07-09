package dev.ashish.EcomPaymentService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PaymentRequestDTO {
    private double amount;
    private UUID orderId;
    private UUID userId;
    private String currencyTag;

    @Override
    public String toString() {
        return "PaymentRequestDTO{" +
                "amount=" + amount +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", currencyTag='" + currencyTag + '\'' +
                '}';
    }
}
