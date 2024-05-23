package dev.ashish.EcomPaymentService.entity;

import dev.ashish.EcomPaymentService.entity.Enum.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Payment extends BaseModel {
    private String transactionId;
    private UUID userId;

    @OneToOne
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private double amount;
    private UUID orderId;
}
