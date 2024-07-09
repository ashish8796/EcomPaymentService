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
    private final RazorpayClient razorpayClient;

    @Autowired
    public RazorpayPaymentLinkGenerator(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public PaymentLinkResponse generatePaymentLink(Payment payment) {
        try {
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", payment.getAmount());
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("accept_partial", true);
            paymentLinkRequest.put("first_min_partial_amount", payment.getAmount());

            // Set expire_by to 15 minutes in the future and log it
            long expireBy = Instant.now().plus(20, ChronoUnit.MINUTES).getEpochSecond();
            System.out.println("Expire By (Unix timestamp): " + expireBy);
            paymentLinkRequest.put("expire_by", expireBy);

//            paymentLinkRequest.put("expire_by", Instant.now().plus(15, ChronoUnit.MINUTES).getEpochSecond());
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

            PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);

            System.out.println("Razorpay Payment Link object: " + paymentLink);

            // Get the amount as an Integer and then convert to double
            Integer amount = (Integer) paymentLink.get("amount");
            double amountDouble = amount.doubleValue();

            return new RazorpayPaymentLinkResponse(
                    paymentLink.get("short_url"),
                    paymentLink.get("id"),
                    paymentLink.get("currency"),
                    amountDouble,
                    paymentLink.get("description"));
        } catch (RazorpayException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }


    }
}
