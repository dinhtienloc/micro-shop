package vn.locdt.order.model;

import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "total_amt")
	private Double totalAmt;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private final User user;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails;
}
