package dev.ashish.EcomPaymentService.service;

import dev.ashish.EcomPaymentService.dto.PaymentRequestDTO;
import dev.ashish.EcomPaymentService.dto.PaymentResponseDTO;
import dev.ashish.EcomPaymentService.entity.Enum.PaymentStatus;
import dev.ashish.EcomPaymentService.entity.Payment;
import dev.ashish.EcomPaymentService.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public PaymentResponseDTO createPayment(PaymentRequestDTO requestDTO) {
        Payment savedPayment = paymentRepository.findByOrderId(requestDTO.getOrderId());

        //handling idempotency for same order id
        if(savedPayment == null) {
            Payment payment = new Payment();

            payment.setPaymentStatus(PaymentStatus.SUCCESS);
            payment.setAmount(requestDTO.getAmount());
            payment.setOrderId(requestDTO.getOrderId());
            payment.setTransactionId(UUID.randomUUID().toString());

            return PaymentResponseDTO.from(paymentRepository.save(payment));
        }else {
            return PaymentResponseDTO.from(savedPayment);
        }
    }
}
