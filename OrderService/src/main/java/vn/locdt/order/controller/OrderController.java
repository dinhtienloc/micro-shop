package vn.locdt.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import vn.locdt.order.model.Order;
import vn.locdt.order.producer.OrderPublisher;
import vn.locdt.order.service.OrderService;

@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class OrderController {
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);

	private final OrderPublisher orderProducer;
	private final OrderService orderService;

	@PostMapping("/{userId}")
	public Order createOrder(@PathVariable Long userId) {
		return this.orderService.placeOrder(userId);
	}

	@GetMapping("")
	public Page<Order> findOrder(@RequestParam Integer page, @RequestParam Integer size) {
		return this.orderService.findAllOrders(page, size);
	}

	@GetMapping("/{id}")
	public Order findOrder(@PathVariable Long id) {
		return this.orderService.findOrder(id);
	}

	@GetMapping("/test")
	public ResponseEntity<String> test() {
		/* Sending to Message Queue */
		try {
			orderProducer.sendMessage("", "Hello world");
			return new ResponseEntity<>("IN_QUEUE", HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while sending message to the queue. Exception= {}", ex);
			return new ResponseEntity<>("MESSAGE_QUEUE_SEND_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
