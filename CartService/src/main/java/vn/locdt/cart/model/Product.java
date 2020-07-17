package vn.locdt.cart.model;

import java.io.Serializable;

import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

	private static final long serialVersionUID = -7382840368670330774L;

	@Indexed
	private Long id;
	private String name;
	private String price;
}
