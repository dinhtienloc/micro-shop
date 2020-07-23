package vn.locdt.order.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItem {
	private Product product;
	private int quantity;

	public CartItem(Cart cart, Product product, int quantity) {
		this.quantity = quantity;
		this.product = product;
		cart.getCartItems().add(this);
	}
}
