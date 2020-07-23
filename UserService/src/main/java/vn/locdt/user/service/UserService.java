package vn.locdt.user.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.locdt.user.model.Order;
import vn.locdt.user.model.User;
import vn.locdt.user.repository.UserRepository;
import vn.locdt.user.service.UserService;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User findUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public Page<User> findAllUsers(Integer page, Integer size) {
		return userRepository.findAll(PageRequest.of(page, size));
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User updateUser(User user) {
		return userRepository.save(user);
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	public boolean validateBalance(User user, double totalAmt) {
		return user.getBalance() != null && user.getBalance() >= totalAmt;
	}
	
	public void paidOrder(User user, double totalAmt) {
		user.setBalance(user.getBalance() - totalAmt);
	}

}
