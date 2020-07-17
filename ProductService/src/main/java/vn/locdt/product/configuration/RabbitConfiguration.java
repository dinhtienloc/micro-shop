package vn.locdt.product.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class RabbitConfiguration {
	@Value("${order.queue.name}")
	private String queueName;

	@Value("${order.exchange.name}")
	private String exchangeName;

	@Value("${order.routing.key}")
	private String routingKey;
}
