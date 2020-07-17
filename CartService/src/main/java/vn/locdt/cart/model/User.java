package vn.locdt.cart.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class User implements Serializable {

	private static final long serialVersionUID = -6178054163022548905L;

	private Long id;
	private String name;
	private String username;
	private String password;
	private LocalDateTime createdAt;
}