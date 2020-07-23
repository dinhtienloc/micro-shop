package vn.locdt.product.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import vn.locdt.product.exception.ProductNotFoundException;
import vn.locdt.product.model.Order;
import vn.locdt.product.model.Order.OrderStatus;
import vn.locdt.product.model.OrderDetail;
import vn.locdt.product.model.Product;
import vn.locdt.product.repository.ProductRepository;
import vn.locdt.product.service.ProductService;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

	private final ProductRepository productRepository;
	
	public boolean validateOrderProductQuantity(Order order) {
		for (OrderDetail item : order.getOrderDetails()) {
			Long productId = item.getProductId();
			Long remainingQuantity = this.getRemainingQuantity(productId);
			long orderedQuantity = item.getQuantity();
			if (orderedQuantity == 0 || remainingQuantity < orderedQuantity) {
				System.out.println(String.format("Invalid product quantity. Id=%s, Quantity=%s", productId, orderedQuantity));
				return false;
			}
		}
		
		return true;
	}
	
	public void updateProductQuantity(Order order) {
		for (OrderDetail item : order.getOrderDetails()) {
			Long productId = item.getProductId();
			Product product = this.findById(productId);
			long orderedQuantity = item.getQuantity();
			product.setRemainingQuantity(product.getRemainingQuantity()-orderedQuantity);
		}
	}
	
	public void reverseProductQuantity(Order order) {
		for (OrderDetail item : order.getOrderDetails()) {
			Long productId = item.getProductId();
			Product product = this.findById(productId);
			long orderedQuantity = item.getQuantity();
			product.setRemainingQuantity(product.getRemainingQuantity()+orderedQuantity);
		}
	}
	

	public Product findById(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	public Page<Product> findAll(Integer page, Integer size) {
		return productRepository.findAll(PageRequest.of(page, size));
	}

	public void save(Product product) {
		productRepository.save(product);
	}

	public void save(List<Product> products) {
		for (Product p : products) {
			productRepository.save(p);
		}
	}

	public void delete(Long id) {
		productRepository.deleteById(id);
	}

	public Long getRemainingQuantity(Long id) {
		return productRepository.getRemainingQuantity(id);
	}

}
