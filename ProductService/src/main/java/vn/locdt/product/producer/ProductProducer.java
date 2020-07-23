package vn.locdt.product.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import vn.locdt.product.configuration.RabbitConfiguration;
import vn.locdt.product.message.Message;
import vn.locdt.product.model.Order;

@Component
@RequiredArgsConstructor
public class ProductProducer {
	private static final Logger log = LoggerFactory.getLogger(ProductProducer.class);

	private final RabbitTemplate rabbitTemplate;
	private final RabbitConfiguration rabbitConfiguration;

	public void sendMessage(String queueName, Object data) {
		log.info("Sending message to the queue using routingKey {}. Message= {}", queueName, data);

		rabbitTemplate.convertAndSend(queueName, data);

		log.info("The message has been sent to the queue.");
	}
	
	public void sendProductReserveEvent(Message<Order> order) {
		this.sendMessage(this.rabbitConfiguration.getProductReserveQueue(), order);
	}
	
	public void sendProductCancelEvent(Message<Order> order) {
		this.sendMessage(this.rabbitConfiguration.getProductCancelQueue(), order);
	}
}
