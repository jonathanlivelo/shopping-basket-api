package com.sample.shopping.basket.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.shopping.basket.api.model.Item;
import com.sample.shopping.basket.api.repository.ItemRepository;

@Service
public class ItemService {
@Autowired
private ItemRepository itemRepository;

public Item get(Integer id) {
	return itemRepository.findById(id).orElse(null);
}
}
