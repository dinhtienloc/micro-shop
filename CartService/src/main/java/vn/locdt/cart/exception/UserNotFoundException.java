package vn.locdt.cart.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(Long userId) {
		super(String.format("User with id %s is not found!, userId"));
	}
}
