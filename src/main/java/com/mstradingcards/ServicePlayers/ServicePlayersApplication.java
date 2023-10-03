package com.mstradingcards.ServicePlayers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.mstradingcards.ServicePlayers"})
@EntityScan("com.mstradingcards.ServicePlayers.models")
@EnableJpaRepositories("com.mstradingcards.ServicePlayers.repository")
@ConfigurationPropertiesScan("com.mstradingcards.ServicePlayers.config")
public class ServicePlayersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicePlayersApplication.class, args);
	}

}
