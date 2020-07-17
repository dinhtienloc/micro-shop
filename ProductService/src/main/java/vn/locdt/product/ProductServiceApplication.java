package vn.locdt.product;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableRabbit
@RequiredArgsConstructor
public class ProductServiceApplication {

	private final vn.locdt.product.configuration.RabbitConfiguration rabbitConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Bean
	Queue queue() {
		return new Queue(rabbitConfiguration.getQueueName(), false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(rabbitConfiguration.getExchangeName());
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(rabbitConfiguration.getRoutingKey());
	}

}
