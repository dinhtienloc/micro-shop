package vn.locdt.product.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetail implements Serializable {
	private int quantity;
	private Long productId;
}
