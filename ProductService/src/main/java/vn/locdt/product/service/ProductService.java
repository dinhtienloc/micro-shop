package vn.locdt.product.service;

import java.util.List;

import org.springframework.data.domain.Page;

import vn.locdt.product.model.Product;

public interface ProductService {
	Product findById(Long id);

	Page<Product> findAll(Integer page, Integer size);

	void save(Product product);

	void save(List<Product> products);

	void delete(Long id);

	Long getRemainingQuantity(Long id);
}
