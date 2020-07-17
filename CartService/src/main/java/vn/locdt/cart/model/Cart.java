package vn.locdt.cart.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@RedisHash("cart")
public class Cart {

	@Id
	@Indexed
	private final Long userId;
	private List<CartItem> cartItems;
	private Double totalAmt;

}
