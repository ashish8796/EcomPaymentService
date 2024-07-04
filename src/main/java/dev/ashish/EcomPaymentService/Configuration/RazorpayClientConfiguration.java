package dev.ashish.EcomPaymentService.Configuration;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayClientConfiguration {
    @Value("${razorpay.client.key}")
    private String razorpayClientKeY;

    @Value("${razorpay.client.secret}")
    private String razorpayClientSecret;


    @Bean
    public RazorpayClient getRazorpayClient() throws RazorpayException {
        return new RazorpayClient(razorpayClientKeY, razorpayClientSecret);
    }
}
