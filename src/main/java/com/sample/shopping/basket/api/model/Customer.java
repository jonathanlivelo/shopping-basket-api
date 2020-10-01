package com.sample.shopping.basket.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="customer")
public class Customer {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	@OneToMany(targetEntity = Basket.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_baskets", referencedColumnName = "id")
	private List<Basket>baskets;
}