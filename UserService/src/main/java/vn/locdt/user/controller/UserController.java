package vn.locdt.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.locdt.user.model.User;
import vn.locdt.user.service.UserService;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/{id}")
	public User findById(@PathVariable Long id) {
		return userService.findUserById(id);
	}

	@GetMapping("")
	public Page<User> findAll(@RequestParam Integer page, @RequestParam Integer size) {
		return userService.findAllUsers(page, size);
	}

	@PostMapping("")
	public void saveUser(@RequestBody User user) {
		userService.saveUser(user);
	}

	@PutMapping("/{id}")
	public void updateUser(@PathVariable Long id, @RequestBody User user) {
		userService.updateUser(user);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@RequestParam Long id) {
		userService.deleteUser(id);
	}
}
