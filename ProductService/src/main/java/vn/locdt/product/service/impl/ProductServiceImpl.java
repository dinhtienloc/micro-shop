package vn.locdt.product.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import vn.locdt.product.model.Product;
import vn.locdt.product.repository.ProductRepository;
import vn.locdt.product.service.ProductService;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Override
	public Product findById(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public Page<Product> findAll(Integer page, Integer size) {
		return productRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public void save(Product product) {
		productRepository.save(product);
	}

	@Override
	public void save(List<Product> products) {
		for (Product p : products) {
			productRepository.save(p);
		}
	}

	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public Long getRemainingQuantity(Long id) {
		return productRepository.getRemainingQuantity(id);
	}

}
