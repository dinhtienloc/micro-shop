package vn.locdt.product.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.locdt.product.model.Product;
import vn.locdt.product.service.ProductService;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/products", produces = MediaType.APPLICATION_JSON)
public class ProductController {

	private final ProductService productService;

	@GetMapping("")
	public Page<Product> find(@RequestParam Integer page, @RequestParam Integer size) {
		return productService.findAll(page, size);
	}

	@GetMapping("/{id}")
	public Product find(@PathVariable Long id) {
		return productService.findById(id);
	}

	@PostMapping("")
	public void save(@RequestBody List<Product> products) {
		productService.save(products);
	}

	@PutMapping("/{id}")
	public void update(@PathVariable Long id, @RequestBody Product product) {
		productService.save(product);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		productService.delete(id);
	}
}
