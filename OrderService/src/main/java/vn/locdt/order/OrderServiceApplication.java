package vn.locdt.order;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

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
	Queue orderCreateQueue() {
		return new Queue(rabbitConfiguration.getOrderCreatedQueue(), false);
	}
	
	@Bean
	Queue orderCancelQueue() {
		return new Queue(rabbitConfiguration.getOrderCanceledQueue(), false);
	}
	
	@Bean
	Queue productReserveQueue() {
		return new Queue(rabbitConfiguration.getProductReserveQueue(), false);
	}
	
	@Bean
	Queue productCancelQueue() {
		return new Queue(rabbitConfiguration.getProductCancelQueue(), false);
	}
	
	@Bean
	Queue paymentSuccessQueue() {
		return new Queue(rabbitConfiguration.getPaymentSuccessQueue(), false);
	}
	
	@Bean
	Queue paymentFailureQueue() {
		return new Queue(rabbitConfiguration.getPaymentFailureQueue(), false);
	}
	
	@Bean
	public MessageConverter producerJsonMessageConverter(){
	    return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public RabbitTemplate configureRabbitTemplate(ConnectionFactory connectionFactory) {
	    RabbitTemplate template = new RabbitTemplate(connectionFactory);
	    template.setMessageConverter(producerJsonMessageConverter());
	    return template;
	}
	
	@Bean
	public MappingJackson2MessageConverter consumerJsonMessageConverter(){
	    return new MappingJackson2MessageConverter();
	}
	
	@Bean
	public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
	    DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
	    factory.setMessageConverter(consumerJsonMessageConverter());
	    return factory;
	}
	
	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
	    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
	    factory.setConnectionFactory(connectionFactory);
	    factory.setMessageConverter(producerJsonMessageConverter());
	    return factory;
	}

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
