package vn.locdt.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.locdt.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
