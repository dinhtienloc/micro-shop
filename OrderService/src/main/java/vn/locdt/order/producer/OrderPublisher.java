package vn.locdt.order.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import vn.locdt.order.configuration.RabbitConfiguration;
import vn.locdt.order.message.Message;

@Component
@RequiredArgsConstructor
public class OrderPublisher {

	private static final Logger log = LoggerFactory.getLogger(OrderPublisher.class);

	private final RabbitTemplate rabbitTemplate;
	private final RabbitConfiguration rabbitConfiguration;

	public void sendMessage(String queueName, Object data) {
		log.info("Sending message to the queue using routingKey {}. Message= {}", queueName, data);

		rabbitTemplate.convertAndSend(queueName, data);

		log.info("The message has been sent to the queue.");
	}
	
	public void sendOrderCreatedEvent(Message<?> mes) {
		this.sendMessage(this.rabbitConfiguration.getOrderCreatedQueue(), mes);
	}
}
