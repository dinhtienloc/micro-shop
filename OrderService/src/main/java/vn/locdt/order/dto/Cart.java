package vn.locdt.order.dto;

import java.util.List;

import lombok.Data;
import vn.locdt.order.model.User;

@Data
public class Cart {
	private Long id;
	private Double totalAmt;
	private User user;
	private List<CartItem> cartItems;
}
