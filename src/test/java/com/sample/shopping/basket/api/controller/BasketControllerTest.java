package com.sample.shopping.basket.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sample.shopping.basket.api.dto.ItemDto;
import com.sample.shopping.basket.api.model.Basket;
import com.sample.shopping.basket.api.model.Customer;
import com.sample.shopping.basket.api.model.Item;
import com.sample.shopping.basket.api.service.BasketService;
import com.sample.shopping.basket.api.service.CustomerService;
import com.sample.shopping.basket.api.service.ItemService;

@SpringBootTest
public class BasketControllerTest {
	@Mock
	private BasketService basketService;
	@Mock
	private CustomerService customerService;
	@Mock
	private ItemService itemService;	
	@InjectMocks
    private BasketController basketController = new BasketController();
    @BeforeEach
    void setMockOutput() {
    	Basket basket =new Basket();
    	List<Item> items = new ArrayList<Item>();
    	Item item=new Item();
    	item.setPrice(1000);
    	items.add(item);
		basket.setItems(items );
        when(basketService.get(1)).thenReturn(basket);
        when(basketService.get(2)).thenReturn(null);
        Customer customer =new Customer();
        List<Basket> baskets= new ArrayList<Basket>();
        baskets.add(basket);
		customer.setBaskets(baskets);
        when(customerService.getCustomer(1)).thenReturn(customer);
        when(customerService.getCustomer(2)).thenReturn(null);
        when(itemService.get(1)).thenReturn(new Item());
    }

    @DisplayName("Test BasketController create All Valid parameter")
    @Test
    void testCreate() {
    	ResponseEntity<String> response  = basketController.create("1", "1");
    	assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    
    @DisplayName("Test BasketController create Customer donot exist")
    @Test
    void testCreateCustomerDonotExist() {
    	ResponseEntity<String> response  = basketController.create("2", "1");
    	assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
    @DisplayName("Test BasketController add Item to basket with Valid Parameter")
    @Test
    void testAddItemToBasket() {
    	ResponseEntity<String> response  = basketController.add("1", new ItemDto());
    	assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    
    @DisplayName("Test BasketController add Item to basket with Valid Parameter")
    @Test
    void testAddItemToBasketBasketNotExist() {
    	ResponseEntity<String> response  = basketController.add("2", new ItemDto());
    	assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
    @DisplayName("Test BasketController delete Item to basket with basket do not exist")
    @Test
    void testDeleteItemToBasket() {
    	ResponseEntity<String> response  = basketController.delete("1", "1");
    	assertEquals(response.getStatusCode(), HttpStatus.OK);
    }      
    @DisplayName("Test BasketController delete Item to basket with basket do not exist")
    @Test
    void testAddItemToBasketNotExist() {
    	ResponseEntity<String> response  = basketController.delete("2", "1");
    	assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }    
    
    @DisplayName("Test BasketController delete Item to basket with basket exist item not exist")
    @Test
    void testAddItemToBasketExistItemNotExist() {
    	ResponseEntity<String> response  = basketController.delete("1", "2");
    	assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
    @DisplayName("Test BasketController get Basket Items")
    @Test
    void testGetBasketItem() {
    	ResponseEntity<List<ItemDto>> response  = basketController.getItems("1");
    	assertEquals(response.getStatusCode(), HttpStatus.OK);
    	assertEquals(response.getBody().size(),1);
    }
    @DisplayName("Test BasketController get Basket Items Basket not Exist")
    @Test
    void testGetBasketItemBasketNotExist() {
    	ResponseEntity<List<ItemDto>> response  = basketController.getItems("2");
    	assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
    
    @DisplayName("Test BasketController get Customer Basket Items Total Price")
    @Test
    void testGetBasketItemTotalPrice() {
    	ResponseEntity<Double> response  = basketController.getTotal("1");
    	assertEquals(response.getStatusCode(), HttpStatus.OK);
    	assertEquals(response.getBody().doubleValue(),1000);
    }
    @DisplayName("Test BasketController get Customer Basket Items Total Price Customer Not Exist")
    @Test
    void testGetBasketItemTotalPriceCustomerNotExist() {
    	ResponseEntity<Double> response  = basketController.getTotal("2");
    	assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }     
}
