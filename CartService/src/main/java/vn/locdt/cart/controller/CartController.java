package vn.locdt.cart.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.locdt.cart.model.Cart;
import vn.locdt.cart.service.CartService;

@RestController
@RequestMapping(path = "/cart", produces = MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class CartController {
	private final CartService cartService;

	@GetMapping(path = "/{userId}")
	public Cart findCart(@PathVariable Long userId) {
		return this.cartService.findByUserId(userId);
	}

	@DeleteMapping(path = "/{userId}")
	public void deleteCart(@PathVariable Long userId) {
		this.cartService.deleteCart(userId);
	}

	@PostMapping(path = "/{userId}")
	public Cart updateCart(@PathVariable Long userId, @RequestBody Cart updatedCart) {
		return this.cartService.updateCart(userId, updatedCart);
	}
}
