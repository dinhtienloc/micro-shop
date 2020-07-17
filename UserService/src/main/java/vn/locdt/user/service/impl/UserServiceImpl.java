package vn.locdt.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import vn.locdt.user.model.User;
import vn.locdt.user.repository.UserRepository;
import vn.locdt.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public Page<User> findAllUsers(Integer page, Integer size) {
		return userRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}
