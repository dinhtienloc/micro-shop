package vn.locdt.cart.service;

import vn.locdt.cart.model.Cart;

public interface CartService {
	Cart findByUserId(Long userId);

	Cart createCart(Long userId);

	Cart updateCart(Long userId, Cart updatedCart);

	void deleteCart(Long userId);
}
