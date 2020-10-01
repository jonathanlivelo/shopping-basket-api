package com.sample.shopping.basket.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.shopping.basket.api.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
