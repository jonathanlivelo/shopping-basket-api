package com.sample.shopping.basket.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.shopping.basket.api.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
