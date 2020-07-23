package vn.locdt.order.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.locdt.order.message.Message;
import vn.locdt.order.model.Cart;
import vn.locdt.order.model.CartItem;
import vn.locdt.order.model.Order;
import vn.locdt.order.model.OrderDetail;
import vn.locdt.order.model.Order.OrderStatus;
import vn.locdt.order.producer.OrderPublisher;
import vn.locdt.order.repository.CartRepository;
import vn.locdt.order.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

	
	private final OrderRepository orderRepository;
	private final CartRepository cartRepository;
	private final OrderPublisher orderPublisher;

	public Page<Order> findAllOrders(Integer page, Integer size) {
		return orderRepository.findAll(PageRequest.of(page, size));
	}

	public Order findOrder(Long id) {
		return orderRepository.findById(id).orElse(null);
	}

	public Order placeOrder(Long userId) {
		Cart userCart = this.cartRepository.findByUserId(userId);
		
		Order order = new Order(userId);
		order.setStatus(OrderStatus.PENDING);
		for (CartItem item : userCart.getCartItems()) {
			new OrderDetail(order, item);
		}
		
		this.orderPublisher.sendOrderCreatedEvent(new Message<>(order));
		return order;
	}
	
	public void updateOrderStatus(Order order, OrderStatus status, String remarks) {
		if (order == null) {
			return;
		}
		
		order.setRemarks(remarks);
		order.setStatus(status);
	}
	
	public void confirmOrder(Order order) {
		this.updateOrderStatus(order, OrderStatus.CONFIRMED, null);
		this.orderRepository.save(order);
	}
}
