package com.example.MarketPost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class MarketPostApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketPostApplication.class, args);
	}

}
