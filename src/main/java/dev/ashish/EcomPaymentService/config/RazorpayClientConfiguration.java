package dev.ashish.EcomPaymentService.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import jakarta.annotation.PostConstruct;
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
        System.out.println("Razorpay Client Key: " + razorpayClientKeY);
        System.out.println("Razorpay Client Secret: " + razorpayClientSecret);
        return new RazorpayClient(razorpayClientKeY, razorpayClientSecret);
    }
}
