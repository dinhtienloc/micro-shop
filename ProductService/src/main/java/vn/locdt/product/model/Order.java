package vn.locdt.product.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Order implements Serializable {
	
	private String id;
	private Long userId;
	private double totalAmt;
	private List<OrderDetail> orderDetails;
	private OrderStatus status;
	private String remarks;
	
	public enum OrderStatus {
		PENDING,
		CONFIRMED,
		DELIVERING,
		COMPLETED;
	}
}
