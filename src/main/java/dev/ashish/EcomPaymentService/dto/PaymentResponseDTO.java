package dev.ashish.EcomPaymentService.dto;

import dev.ashish.EcomPaymentService.entity.Enum.PaymentStatus;
import dev.ashish.EcomPaymentService.entity.Payment;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PaymentResponseDTO {
    private UUID orderId;
    private UUID transactionId;
    private double amount;
    private PaymentStatus paymentStatus;
    private UUID paymentId;

    public static PaymentResponseDTO from (Payment payment) {
        PaymentResponseDTO responseDTO = new PaymentResponseDTO();

        responseDTO.setPaymentId(payment.getId());
        responseDTO.setAmount(payment.getAmount());
        responseDTO.setPaymentStatus(payment.getPaymentStatus());
        responseDTO.setOrderId(payment.getOrderId());
        responseDTO.setTransactionId(payment.getTransactionId());

        return responseDTO;
    }

}
