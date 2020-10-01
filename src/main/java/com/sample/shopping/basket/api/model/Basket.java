package com.sample.shopping.basket.api.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="basket")
public class Basket {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	@ManyToOne(targetEntity = Customer.class)
	private Customer customer;
	@OneToMany(targetEntity = Item.class)
	@JoinColumn(name = "basket_items", referencedColumnName = "id")
	private List<Item> items;
}