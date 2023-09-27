package com.mstradingcards.ServiceUsers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.mstradingcards.ServiceUsers"})
@EntityScan("com.mstradingcards.ServiceUsers.models")
@EnableJpaRepositories("com.mstradingcards.ServiceUsers.repository")
public class ServiceUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceUsersApplication.class, args);
	}

}
