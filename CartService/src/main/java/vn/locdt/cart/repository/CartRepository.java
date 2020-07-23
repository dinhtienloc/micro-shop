package vn.locdt.cart.repository;

import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

import vn.locdt.cart.model.Cart;

@EnableRedisRepositories
public interface CartRepository extends CrudRepository<Cart, Long> {
	Cart findByUserId(Long userId);
}
