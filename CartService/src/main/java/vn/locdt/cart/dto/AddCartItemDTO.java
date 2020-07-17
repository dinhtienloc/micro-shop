package vn.locdt.cart.dto;

import lombok.Data;

@Data
public class AddCartItemDTO {
	private Long userId;
	private Long productId;
	private int quantity;
}
