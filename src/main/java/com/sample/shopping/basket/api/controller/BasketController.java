package com.sample.shopping.basket.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

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

@RestController("/basket")
public class BasketController {
	private BasketService basketService;
	private CustomerService customerService;
	@SuppressWarnings("rawtypes")
	@PostMapping("/{customerId}/{name}")
	public ResponseEntity create  (@PathParam("customerId") String customerId,@PathParam("name")  String name) {
		Customer customer = customerService.getCustomer(Integer.parseInt(customerId));
		Basket basket=new Basket();
		basket.setName(name);
		basket.setCustomer(customer);
		basketService.save(basket);
		return ResponseEntity.accepted().build();
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/item/{basketid}")
	public ResponseEntity add(@PathParam("basketId") String basketId, @RequestBody ItemDto itemDto) {
		Basket basket=basketService.get(Integer.parseInt(basketId));
		Item item = new Item();
		item.setName(itemDto.getName());
		item.setPrice(itemDto.getPrice());
		basket.getItems().add(item);			
		basketService.save(basket);
		return ResponseEntity.accepted().build();
	}	
	
	@DeleteMapping("/item/{basketid}/{itemId}")
	public ResponseEntity delete(@PathParam("basketId") String basketId, @RequestBody ItemDto itemDto) {
		Basket basket=basketService.get(Integer.parseInt(basketId));
		Item item = new Item();
		item.setName(itemDto.getName());
		item.setPrice(itemDto.getPrice());
		basket.getItems().remove(item);
		basketService.save(basket);
		return ResponseEntity.accepted().build();
	}	
	
	@GetMapping("/item/{basketid}")
	public ResponseEntity<List<ItemDto>> getItems(@PathParam("basketId") String basketId) {
		Basket basket=basketService.get(Integer.parseInt(basketId));
		List<ItemDto> items = new ArrayList<ItemDto>();
		for(Item item: basket.getItems()) {
			ItemDto itemDTO=new ItemDto();
			itemDTO.setId(item.getId());
			itemDTO.setName(item.getName());
			itemDTO.setPrice(item.getPrice());
			items.add(itemDTO);
		}		 
		return ResponseEntity.ok(items);
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Double> getTotal(@PathParam("customerId") String customerId) {
		Customer customer = customerService.getCustomer(Integer.parseInt(customerId));
		Double total=Double.valueOf(0);
		for(Basket basket: customer.getBaskets()) {			
			for(Item item: basket.getItems()) {
				total=total+item.getPrice();
			}
		}		 
		return ResponseEntity.ok(total);
	}			
}

