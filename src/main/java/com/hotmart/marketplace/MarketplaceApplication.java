package com.hotmart.marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MarketplaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketplaceApplication.class, args);
	}

}
