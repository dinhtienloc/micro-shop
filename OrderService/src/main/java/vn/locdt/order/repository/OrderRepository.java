package vn.locdt.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import vn.locdt.order.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {

}
