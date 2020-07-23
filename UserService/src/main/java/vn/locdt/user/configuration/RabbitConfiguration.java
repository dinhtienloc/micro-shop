package vn.locdt.user.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class RabbitConfiguration {
	
	@Value("${order.create.queue}")
	private String orderCreatedQueue;
	
	@Value("${order.cancel.queue}")
	private String orderCanceledQueue;
	
	@Value("${product.reserve.queue}")
	private String productReserveQueue;
	
	@Value("${product.cancel.queue}")
	private String productCancelQueue;
	
	@Value("${payment.success.queue}")
	private String paymentSuccessQueue;
	
	@Value("${payment.failure.queue}")
	private String paymentFailureQueue;
}
