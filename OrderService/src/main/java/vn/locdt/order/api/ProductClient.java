package vn.locdt.order.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.locdt.order.model.Product;

@FeignClient(name = "eshop")
public interface ProductClient {
	@GetMapping(value = "/products")
	Page<Product> getProducts(@RequestParam("page") Integer page, @RequestParam("size") Integer size);

	@GetMapping(value = "/products/{id}")
	Product findProduct(@PathVariable("id") Long id);

	@PostMapping(value = "/internal/products/quantity/{id}")
	Long getRemainingQuantity(@PathVariable("id") Long productId);
}
