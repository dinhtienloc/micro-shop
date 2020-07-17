package vn.locdt.order.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.locdt.order.dto.CartItem;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetail {
	@EmbeddedId
	private Pk pk;

	@Column(name = "quantity")
	private int quantity;

	public OrderDetail(Order order, CartItem item) {
		this.pk = new Pk(order.getId(), product.getId());
		this.quantity = item.getQuantity();
		this.order = order;
		this.product = item.getProduct();
	}

	@ManyToOne
	@JoinColumn(name = "orderId", referencedColumnName = "id", insertable = false, updatable = false)
	private Order order;

	@ManyToOne
	@JoinColumn(name = "productId", referencedColumnName = "id", insertable = false, updatable = false)
	private Product product;

	@Data
	@AllArgsConstructor
	@Embeddable
	private class Pk implements Serializable {

		private static final long serialVersionUID = -4122059221645594837L;

		// @Column(name = "order_id")
		private Long orderId;

		// @Column(name = "product_id")
		private Long productId;
	}

}
