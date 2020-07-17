package vn.locdt.product.component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProductOrderConsumer {

	@RabbitListener(queues = { "${order.queue.name}" })
	public void receive(@Payload Object message) {
		System.out.println(message);
	}
}
