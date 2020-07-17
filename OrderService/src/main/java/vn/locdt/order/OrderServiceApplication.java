package vn.locdt.order;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import lombok.RequiredArgsConstructor;
import vn.locdt.order.configuration.RabbitConfiguration;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableRabbit
@RequiredArgsConstructor
public class OrderServiceApplication {

	private final RabbitConfiguration rabbitConfiguration;

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

//	@Bean
//	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//			MessageListenerAdapter listenerAdapter) {
//		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//		container.setConnectionFactory(connectionFactory);
//		container.setQueueNames(queueName);
//		container.setMessageListener(listenerAdapter);
//		return container;
//	}

//	@Bean
//	MessageListenerAdapter listenerAdapter(Receiver receiver) {
//		return new MessageListenerAdapter(receiver, "receiveMessage");
//	}

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
