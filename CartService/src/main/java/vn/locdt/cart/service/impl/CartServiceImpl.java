package vn.locdt.cart.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import vn.locdt.cart.api.UserClient;
import vn.locdt.cart.exception.UserNotFoundException;
import vn.locdt.cart.model.Cart;
import vn.locdt.cart.repository.CartRepository;
import vn.locdt.cart.service.CartService;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
	private final CartRepository cartRepository;
	private final UserClient userClient;

	@Override
	public Cart findByUserId(Long userId) {
		return this.cartRepository.findByUserId(userId);
	}

	@Override
	public Cart createCart(Long userId) {
		if (this.userClient.findUser(userId) == null) {
			throw new UserNotFoundException(userId);
		} else {
			return this.cartRepository.save(new Cart(userId));
		}
	}

	@Override
	public Cart updateCart(Long userId, Cart updatedCart) {
		if (this.userClient.findUser(userId) == null) {
			throw new UserNotFoundException(userId);
		} else {
			this.cartRepository.save(updatedCart);
			return updatedCart;
		}
	}

	@Override
	public void deleteCart(Long userId) {
		Cart cart = this.findByUserId(userId);
		this.cartRepository.delete(cart);
	}

}
