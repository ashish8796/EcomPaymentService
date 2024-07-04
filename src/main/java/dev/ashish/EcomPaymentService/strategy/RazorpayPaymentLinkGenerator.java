package dev.ashish.EcomPaymentService.strategy;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import dev.ashish.EcomPaymentService.Configuration.RazorpayClientConfiguration;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Concrete strategy
@Component
public class RazorpayPaymentLinkGenerator implements PaymentLinkGeneratorStrategy {
    @Autowired
    private RazorpayClientConfiguration rzpConfiguration;

    @Override
    public PaymentLinkResponse generatePaymentLink() {
        try {
            RazorpayClient rzpClient = rzpConfiguration.getRazorpayClient();
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", 1000);
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("accept_partial", true);
            paymentLinkRequest.put("first_min_partial_amount", 100);
            paymentLinkRequest.put("expire_by", 1691097057);
            paymentLinkRequest.put("reference_id", "TS1989");
            paymentLinkRequest.put("description", "Payment for policy no #23456");
            JSONObject customer = new JSONObject();
            customer.put("name", "+919000090000");
            customer.put("contact", "Gaurav Kumar");
            customer.put("email", "gaurav.kumar@example.com");
            paymentLinkRequest.put("customer", customer);
            JSONObject notify = new JSONObject();
            notify.put("sms", true);
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);
            paymentLinkRequest.put("reminder_enable", true);
            JSONObject notes = new JSONObject();
            notes.put("policy_name", "Jeevan Bima");
            paymentLinkRequest.put("notes", notes);
            paymentLinkRequest.put("callback_url", "https://example-callback-url.com/");
            paymentLinkRequest.put("callback_method", "get");

            PaymentLink payment = rzpClient.paymentLink.create(paymentLinkRequest);

            return new RazorpayPaymentLinkResponse(payment.get("short_url"), payment.get("id"), payment.get("currency"), payment.get("amount"), payment.get("description"));
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }


    }
}
