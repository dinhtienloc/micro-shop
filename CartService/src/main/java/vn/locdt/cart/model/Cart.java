package vn.locdt.cart.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RedisHash("cart")
public class Cart implements Serializable{

	@Id
	@Indexed
	private Long userId;
	private List<CartItem> cartItems;
	
	public Cart(Long userId) {
		this.userId = userId;
	}

}
