package com.sample.shopping.basket.api.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.shopping.basket.api.dto.ItemDto;
import com.sample.shopping.basket.api.model.Basket;
import com.sample.shopping.basket.api.model.Customer;
import com.sample.shopping.basket.api.model.Item;
import com.sample.shopping.basket.api.service.BasketService;
import com.sample.shopping.basket.api.service.CustomerService;
import com.sample.shopping.basket.api.service.ItemService;

@RestController("/basket")
public class BasketController {
	@Autowired
	private BasketService basketService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ItemService itemService;
	@PostMapping("/{customerId}/{name}")
	public ResponseEntity<String> create  (@PathParam("customerId") String customerId,@PathParam("name")  String name) {
		Customer customer = customerService.getCustomer(Integer.parseInt(customerId));
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		Basket basket=new Basket();
		basket.setName(name);
		basket.setCustomer(customer);
		basketService.save(basket);
		return ResponseEntity.ok().build();
	}
	

	@PostMapping("/item/{basketid}")
	public ResponseEntity<String> add(@PathParam("basketId") String basketId, @RequestBody ItemDto itemDto) {
		Basket basket=basketService.get(Integer.parseInt(basketId));
		if (basket == null) {
			return ResponseEntity.notFound().build();
		}
		Item item = new Item();
		item.setName(itemDto.getName());
		item.setPrice(itemDto.getPrice());
		if (basket.getItems() == null) {
			basket.setItems(new ArrayList<Item>());
		}
		basket.getItems().add(item);			
		basketService.save(basket);
		return ResponseEntity.ok().build();
	}	
	
	@DeleteMapping("/item/{basketid}/{itemId}")
	public ResponseEntity<String> delete(@PathParam("basketId") String basketId, @PathParam("itemId") String itemId) {
		Basket basket=basketService.get(Integer.parseInt(basketId));
		if (basket == null) {
			return ResponseEntity.notFound().build();
		}
		Item item = itemService.get(Integer.parseInt(itemId));	
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		if(basket.getItems()!= null && basket.getItems().size()>0) {
			basket.getItems().remove(item);
			basketService.save(basket);
			return ResponseEntity.ok().build();			
		}else {
			return ResponseEntity.notFound().build();
		}

	}	
	
	@GetMapping("/item/{basketid}")
	public ResponseEntity<List<ItemDto>> getItems(@PathParam("basketId") String basketId) {
		Basket basket=basketService.get(Integer.parseInt(basketId));
		if (basket == null) {
			return ResponseEntity.notFound().build();
		}
		List<ItemDto> items = basket.getItems().stream().map(item->{
		 ItemDto itemDTO=new ItemDto();
		 itemDTO.setId(item.getId());
		 itemDTO.setName(item.getName());
		 itemDTO.setPrice(item.getPrice());
		 return itemDTO;
		}).collect(Collectors.toList());
		return ResponseEntity.ok(items);
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Double> getTotal(@PathParam("customerId") String customerId) {
		Customer customer = customerService.getCustomer(Integer.parseInt(customerId));
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		Double total=Double.valueOf(0);
		for(Basket basket: customer.getBaskets()) {			
			for(Item item: basket.getItems()) {
				total=total+item.getPrice();
			}
		}		 
		return ResponseEntity.ok(total);
	}			
}

