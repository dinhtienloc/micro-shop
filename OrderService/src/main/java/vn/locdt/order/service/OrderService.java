package vn.locdt.order.service;

import org.springframework.data.domain.Page;

import vn.locdt.order.model.Order;

public interface OrderService {
	Page<Order> findAllOrders(Integer page, Integer size);

	Order findOrder(Long id);

	Order createOrder(Long userId);
}
