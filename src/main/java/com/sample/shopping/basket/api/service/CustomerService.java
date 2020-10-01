package com.sample.shopping.basket.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.shopping.basket.api.model.Customer;
import com.sample.shopping.basket.api.repository.CustomerRepository;

@Service
public class CustomerService {
@Autowired
private CustomerRepository customerRepository;
public void saveCustomer(Customer customer) {
	customerRepository.save(customer);
}
public Customer getCustomer(Integer id) {
	return customerRepository.findById(id).orElse(null);
}
}
