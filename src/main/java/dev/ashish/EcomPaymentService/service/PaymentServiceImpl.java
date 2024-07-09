package dev.ashish.EcomPaymentService.service;

import dev.ashish.EcomPaymentService.config.RazorpayClientConfiguration;
import dev.ashish.EcomPaymentService.dto.PaymentRequestDTO;
import dev.ashish.EcomPaymentService.dto.PaymentResponseDTO;
import dev.ashish.EcomPaymentService.entity.Enum.PaymentStatus;
import dev.ashish.EcomPaymentService.entity.Payment;
import dev.ashish.EcomPaymentService.repository.PaymentRepository;
import dev.ashish.EcomPaymentService.strategy.PaymentLinkGenerator;
import dev.ashish.EcomPaymentService.strategy.PaymentLinkResponse;
import dev.ashish.EcomPaymentService.strategy.RazorpayPaymentLinkGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RazorpayPaymentLinkGenerator razorpayPaymentLinkGenerator;

    @Override
    public PaymentResponseDTO createPayment(PaymentRequestDTO requestDTO) {
        Payment savedPayment = paymentRepository.findByOrderId(requestDTO.getOrderId());
        PaymentLinkGenerator paymentLinkGenerator = new PaymentLinkGenerator(new RazorpayPaymentLinkGenerator(new RazorpayClientConfiguration()));

        //handling idempotency for same order id
        if (savedPayment == null) {
            Payment payment = new Payment();

            payment.setPaymentStatus(PaymentStatus.INPROGRESS);
            payment.setAmount(requestDTO.getAmount());
            payment.setOrderId(requestDTO.getOrderId());
            payment.setTransactionId(UUID.randomUUID().toString());

            PaymentLinkResponse paymentLink = paymentLinkGenerator.generatePaymentLink(payment);

            return PaymentResponseDTO.from(paymentRepository.save(payment), paymentLink.getPaymentLink());
        } else {
            return PaymentResponseDTO.from(savedPayment, "");
        }
    }
}
