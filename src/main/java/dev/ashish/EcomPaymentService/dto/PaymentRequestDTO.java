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
}
