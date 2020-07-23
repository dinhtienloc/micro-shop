package vn.locdt.order.repository;

import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

import vn.locdt.order.model.Cart;

@EnableRedisRepositories
public interface CartRepository extends CrudRepository<Cart, Long> {
	Cart findByUserId(Long userId);
}