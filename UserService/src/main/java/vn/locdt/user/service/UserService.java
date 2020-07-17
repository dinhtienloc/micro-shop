package vn.locdt.user.service;

import org.springframework.data.domain.Page;

import vn.locdt.user.model.User;

public interface UserService {
	User findUserById(Long id);

	Page<User> findAllUsers(Integer page, Integer size);

	User saveUser(User user);

	User updateUser(User user);

	void deleteUser(Long id);
}
