package com.sample.shopping.basket.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.shopping.basket.api.model.Basket;
import com.sample.shopping.basket.api.repository.BasketRepository;

@Service
public class BasketService {
@Autowired
private BasketRepository basketRepository;
public void save(Basket basket) {
	basketRepository.save(basket);
}
public Basket get(Integer id) {
	return basketRepository.findById(id).orElse(null);
}
}
