package com.mstradingcards.ServicePlayers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.mstradingcards.ServiceUsers"})
@EntityScan("com.mstradingcards.ServiceUsers.models")
@EnableJpaRepositories("com.mstradingcards.ServiceUsers.repository")
@ConfigurationPropertiesScan("com.mstradingcards.ServiceUsers.config")
public class ServicePlayersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicePlayersApplication.class, args);
	}

}
