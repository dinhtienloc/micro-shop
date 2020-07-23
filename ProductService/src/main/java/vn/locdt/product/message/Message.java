package vn.locdt.product.message;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message<T> {
	private T payload;
	private String message;
	
	public Message(T payload) {
		this.payload = payload;
	}
	
	public Message(T payload, String message) {
		this(payload);
		this.message = message;
	}
}
