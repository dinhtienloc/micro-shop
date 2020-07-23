package vn.locdt.order.model;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class Product implements Serializable {
	private Long id;
	private String name;
	private String price;
}
