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
    private String transactionId;
    private double amount;
    private PaymentStatus paymentStatus;
    private UUID paymentId;
    private UUID userId;
    private String currencyTag;
    private String paymentLink;

    public static PaymentResponseDTO from (Payment payment, String paymentLink) {
        PaymentResponseDTO responseDTO = new PaymentResponseDTO();

        responseDTO.setPaymentId(payment.getId());
        responseDTO.setAmount(payment.getAmount());
        responseDTO.setPaymentStatus(payment.getPaymentStatus());
        responseDTO.setOrderId(payment.getOrderId());
        responseDTO.setTransactionId(payment.getTransactionId());
        responseDTO.setCurrencyTag(payment.getCurrency().getCurrencyTag());
        responseDTO.setUserId(payment.getUserId());

        return responseDTO;
    }

}
