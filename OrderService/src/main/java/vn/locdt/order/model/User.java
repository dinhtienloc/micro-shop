package vn.locdt.order.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class User implements Serializable {

	private Long id;
	private String name;
	private String username;
	private String password;
	private LocalDateTime createdAt;
}