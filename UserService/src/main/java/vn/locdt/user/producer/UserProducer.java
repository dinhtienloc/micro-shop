package vn.locdt.user.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import vn.locdt.user.configuration.RabbitConfiguration;
import vn.locdt.user.message.Message;
import vn.locdt.user.model.Order;

@Component
@RequiredArgsConstructor
public class UserProducer {
	private static final Logger log = LoggerFactory.getLogger(UserProducer.class);

	private final RabbitTemplate rabbitTemplate;
	private final RabbitConfiguration rabbitConfiguration;

	public void sendMessage(String queueName, Object data) {
		log.info("Sending message to the queue using routingKey {}. Message= {}", queueName, data);

		rabbitTemplate.convertAndSend(queueName, data);

		log.info("The message has been sent to the queue.");
	}
	
	public void sendPaymentSuccessEvent(Message<Order> order) {
		this.sendMessage(this.rabbitConfiguration.getPaymentSuccessQueue(), order);
	}
	
	public void sendPaymentFailureEvent(Message<Order> order) {
		this.sendMessage(this.rabbitConfiguration.getPaymentFailureQueue(), order);
	}
}
