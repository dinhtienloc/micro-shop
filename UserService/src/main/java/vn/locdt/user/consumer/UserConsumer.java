package vn.locdt.user.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import vn.locdt.user.message.Message;
import vn.locdt.user.model.Order;
import vn.locdt.user.model.User;
import vn.locdt.user.producer.UserProducer;
import vn.locdt.user.service.UserService;

@Component
@RequiredArgsConstructor
public class UserConsumer {
	
	private final UserService userService;
	private final UserProducer userProducer;
	
	@RabbitListener(queues = { "${product.reserve.queue}" })
	public void receive(@Payload Message<Order> message) {
		Order order = message.getPayload();
		User user = userService.findUserById(order.getUserId());
		if (user == null) {
			String mes = String.format("User with id %s is not exist", order.getUserId());
			this.userProducer.sendPaymentFailureEvent(new Message<>(order, mes));
		}
		else {
			double totalAmt = order.getTotalAmt();
			if (!this.userService.validateBalance(user, totalAmt)) {
				String mes = "User doesn't have enough balance to paid this order";
				this.userProducer.sendPaymentFailureEvent(new Message<>(order, mes));
			}
			else {
				this.userService.paidOrder(user, totalAmt);
				this.userProducer.sendPaymentSuccessEvent(message);
			}
		}
	}
	
}
