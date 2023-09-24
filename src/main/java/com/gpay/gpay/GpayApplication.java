package com.gpay.gpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class GpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GpayApplication.class, args);
	}

}
