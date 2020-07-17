package vn.locdt.order.dto;

import java.util.List;

import lombok.Data;
import vn.locdt.order.model.Product;
import vn.locdt.order.model.User;

@Data
public class OrderRequest {
	private User user;
	private List<OrderItemRequest> items;

	@Data
	public class OrderItemRequest {
		private Product product;
		private Long quantity;
	}
}
