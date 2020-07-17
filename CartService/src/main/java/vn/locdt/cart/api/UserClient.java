package vn.locdt.cart.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.locdt.cart.model.User;

@FeignClient(name = "user-service")
public interface UserClient {

	@RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
	User findUser(@PathVariable("id") Long id);
}
