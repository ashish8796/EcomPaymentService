package dev.ashish.EcomPaymentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "dev.ashish.EcomPaymentService")
public class EcomPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomPaymentServiceApplication.class, args);
	}

}
