package dev.ashish.EcomPaymentService.strategy;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import dev.ashish.EcomPaymentService.config.RazorpayClientConfiguration;
import dev.ashish.EcomPaymentService.entity.Payment;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

// Concrete strategy
@Component
public class RazorpayPaymentLinkGenerator implements PaymentLinkGeneratorStrategy {
    @Autowired
    private final RazorpayClientConfiguration rzpConfiguration;

    @Autowired
    public RazorpayPaymentLinkGenerator(RazorpayClientConfiguration rzpConfiguration) {
        this.rzpConfiguration = rzpConfiguration;
    }

    @Override
    public PaymentLinkResponse generatePaymentLink(Payment payment) {
        try {
            RazorpayClient rzpClient = rzpConfiguration.getRazorpayClient();
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", payment.getAmount());
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("accept_partial", true);
            paymentLinkRequest.put("first_min_partial_amount", payment.getAmount());
            paymentLinkRequest.put("expire_by", Instant.now().plus(10, ChronoUnit.MINUTES));
            paymentLinkRequest.put("reference_id", payment.getTransactionId());
            paymentLinkRequest.put("description", "Payment for demo purpose.");
            JSONObject customer = new JSONObject();
            customer.put("name", "Ashish Kumar Saini");
            customer.put("contact", "+917500762013");
            customer.put("email", "ashishsaini.ak@gmail.com");
            paymentLinkRequest.put("customer", customer);
            JSONObject notify = new JSONObject();
            notify.put("sms", true);
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);
            paymentLinkRequest.put("reminder_enable", true);
            paymentLinkRequest.put("callback_url", "https://example-callback-url.com/");
            paymentLinkRequest.put("callback_method", "get");

            PaymentLink paymentLink = rzpClient.paymentLink.create(paymentLinkRequest);

            System.out.println(paymentLink);

            return new RazorpayPaymentLinkResponse(paymentLink.get("short_url"), paymentLink.get("id"), paymentLink.get("currency"), paymentLink.get("amount"), paymentLink.get("description"));
        } catch (RazorpayException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }


    }
}
