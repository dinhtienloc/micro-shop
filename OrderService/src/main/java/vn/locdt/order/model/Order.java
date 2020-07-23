package vn.locdt.order.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Document(collection = "orders")
public class Order implements Serializable {
	@Id
	private String id;
	private Long userId;
	private List<OrderDetail> orderDetails;
	private OrderStatus status;
	private String remarks;
	
	public Order(Long userId) {
		this.userId = userId;
		this.orderDetails = new ArrayList<>();
	}
	
	@Getter
	public enum OrderStatus {
		PENDING,
		FAILED,
		CONFIRMED,
		DELIVERING,
		COMPLETED;
	}
}
