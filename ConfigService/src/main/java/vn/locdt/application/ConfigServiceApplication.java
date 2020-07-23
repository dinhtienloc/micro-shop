package vn.locdt.application;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableRabbit
@EnableConfigServer
public class ConfigServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ConfigServiceApplication.class, args);
	}

}
