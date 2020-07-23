package vn.locdt.order.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import vn.locdt.order.message.Message;
import vn.locdt.order.model.Order;
import vn.locdt.order.model.Order.OrderStatus;
import vn.locdt.order.service.OrderService;


@Component
@RequiredArgsConstructor
public class OrderConsumer {
	
	private final OrderService orderService;
	
	@RabbitListener(queues = { "${product.cancel.queue}" })
	public void onProductCancel(@Payload Message<Order> message) {
		Order order = message.getPayload();
		this.orderService.updateOrderStatus(order, OrderStatus.FAILED, message.getMessage());
	}
	
	@RabbitListener(queues = { "${payment.success.queue}" })
	public void onPaymentSuccess(@Payload Message<Order> message) {
		Order order = message.getPayload();
		this.orderService.confirmOrder(order);
	}
	
}
