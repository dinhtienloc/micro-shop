package vn.locdt.product.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.locdt.product.service.ProductService;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/internal/products", produces = MediaType.APPLICATION_JSON)
public class InternalProductController {
	private final ProductService productService;

	@GetMapping("/quantity/{productId}")
	public Long getQuantity(@PathVariable Long productId) {
		return productService.getRemainingQuantity(productId);
	}
	
//	@PostMapping("/readLock/{id}")
//	public void doReadLock(@PathVariable Long id) {
//		productService.doReadLock(id);
//	}
//	
//	@PostMapping("/releaseReadLock/{id}")
//	public void doReadLock(@PathVariable Long id) {
//		productService.doReadLock(id);
//	}
}
