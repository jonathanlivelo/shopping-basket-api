package com.sample.shopping.basket.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.shopping.basket.api.model.Basket;

public interface BasketRepository extends JpaRepository<Basket, Integer> {

}
