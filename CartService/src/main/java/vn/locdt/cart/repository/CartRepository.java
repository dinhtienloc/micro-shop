package vn.locdt.cart.repository;

import org.springframework.data.repository.CrudRepository;

import vn.locdt.cart.model.Cart;

public interface CartRepository extends CrudRepository<Cart, Long> {
	Cart findByUserId(Long userId);
}
