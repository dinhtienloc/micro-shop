package vn.locdt.order.dto;

import lombok.Data;
import vn.locdt.order.model.Product;

@Data
public class CartItem {
	private Product product;
	private int quantity;
}