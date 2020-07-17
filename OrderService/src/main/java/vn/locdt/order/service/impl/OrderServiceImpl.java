package vn.locdt.order.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.locdt.order.api.ProductClient;
import vn.locdt.order.dto.Cart;
import vn.locdt.order.dto.CartItem;
import vn.locdt.order.model.Order;
import vn.locdt.order.model.OrderDetail;
import vn.locdt.order.repository.OrderDetailRepository;
import vn.locdt.order.repository.OrderRepository;
import vn.locdt.order.service.OrderService;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final OrderDetailRepository orderDetailRepository;
	private final ProductClient productClient;

	@Override
	public Page<Order> findAllOrders(Integer page, Integer size) {
		return orderRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public Order findOrder(Long id) {
		return orderRepository.findById(id).orElse(null);
	}

	@Override
	public Order createOrder(Long userId) {
		Cart userCart = this.getUserCart();
		// TODO: validate product remaining quantity
		Order order = new Order(userCart.getUser());
		for (CartItem item : userCart.getCartItems()) {
			OrderDetail detail = new OrderDetail(order, item);
			this.orderDetailRepository.save(detail);
		}
		return this.orderRepository.save(order);
		// TODO: add to message queue for product service and delivery service
	}

	private Cart getUserCart() {
		return new Cart();
	}
}
