package com.sample.shopping.basket.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories
@SpringBootApplication
public class ShoppingBasketApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingBasketApiApplication.class, args);
	}

}
