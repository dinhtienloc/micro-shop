package vn.locdt.order.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import vn.locdt.order.configuration.RabbitConfiguration;

@Component
@RequiredArgsConstructor
public class OrderProducer {

	private static final Logger log = LoggerFactory.getLogger(OrderProducer.class);

	private final RabbitTemplate rabbitTemplate;
	private final RabbitConfiguration rabbitConfiguration;

	public void sendMessage(Object data) {
		String exchange = rabbitConfiguration.getExchangeName();
		String routingKey = rabbitConfiguration.getRoutingKey();

		log.info("Sending message to the queue using routingKey {}. Message= {}", routingKey, data);

		rabbitTemplate.convertAndSend(exchange, routingKey, data);

		log.info("The message has been sent to the queue.");
	}
}
