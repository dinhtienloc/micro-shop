package vn.locdt.product.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import vn.locdt.product.message.Message;
import vn.locdt.product.model.Order;
import vn.locdt.product.producer.ProductProducer;
import vn.locdt.product.service.ProductService;

@Component
@RequiredArgsConstructor
public class ProductOrderConsumer {
	
	private final ProductService productService;
	private final ProductProducer productProducer;
	
	@RabbitListener(queues = { "${order.create.queue}" })
	public void orderCreatedReceive(@Payload Message<Order> message) {
  		Order order = message.getPayload();
		
		if (!this.productService.validateOrderProductQuantity(order)) {
			this.productProducer.sendProductCancelEvent(new Message<>(order));
		}
		else {
			this.productService.updateProductQuantity(order);
			this.productProducer.sendProductReserveEvent(new Message<>(order));
		}
	}
	
	@RabbitListener(queues = { "${payment.failure.queue}" })
	public void paymentFailureReceive(@Payload Message<Order> message) {
		Order order = message.getPayload();
		
		this.productService.reverseProductQuantity(order);
		this.productProducer.sendProductCancelEvent(message);
	}
	
}
