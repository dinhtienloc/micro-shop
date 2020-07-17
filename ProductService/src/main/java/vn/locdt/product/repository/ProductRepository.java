package vn.locdt.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.locdt.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query(value = "select p.remainingQuantity from Product p where p.id = ?1")
	Long getRemainingQuantity(Long id);
}
