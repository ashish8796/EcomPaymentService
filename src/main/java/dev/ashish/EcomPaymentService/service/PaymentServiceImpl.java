package dev.ashish.EcomPaymentService.service;

import dev.ashish.EcomPaymentService.dto.PaymentRequestDTO;
import dev.ashish.EcomPaymentService.dto.PaymentResponseDTO;
import dev.ashish.EcomPaymentService.entity.Enum.PaymentStatus;
import dev.ashish.EcomPaymentService.entity.Payment;
import dev.ashish.EcomPaymentService.repository.PaymentRepository;
import dev.ashish.EcomPaymentService.strategy.PaymentLinkGenerator;
import dev.ashish.EcomPaymentService.strategy.RazorpayPaymentLinkGenerator;
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
        PaymentLinkGenerator paymentLinkGenerator = new PaymentLinkGenerator(new RazorpayPaymentLinkGenerator());

        //handling idempotency for same order id
        if (savedPayment == null) {
            Payment payment = new Payment();

            payment.setPaymentStatus(PaymentStatus.INPROGRESS);
            payment.setAmount(requestDTO.getAmount());
            payment.setOrderId(requestDTO.getOrderId());
            payment.setTransactionId(UUID.randomUUID().toString());

            return PaymentResponseDTO.from(paymentRepository.save(payment));
        } else {
            return PaymentResponseDTO.from(savedPayment);
        }
    }
}
