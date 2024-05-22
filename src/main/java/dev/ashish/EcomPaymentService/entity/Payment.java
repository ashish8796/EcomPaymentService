package dev.ashish.EcomPaymentService.entity;

import dev.ashish.EcomPaymentService.entity.Enum.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Payment extends BaseModel {
    private UUID transactionId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private double amount;
    private UUID orderId;
}
