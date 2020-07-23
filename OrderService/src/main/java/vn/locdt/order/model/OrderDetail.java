package vn.locdt.order.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetail implements Serializable {
	private int quantity;
	private Long productId;
	
	public OrderDetail(Order order, CartItem item) {
		this.quantity = item.getQuantity();
		this.productId = item.getProduct().getId();
		order.getOrderDetails().add(this);
	}
}
