package com.mstradingcards.ServiceUsers.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class UserDTO {

	@NotBlank(message = "Username is required")
	private String username;

	private List<Long> decks;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Long> getDecks() {
		return decks;
	}

	public void setDecks(List<Long> decks) {
		this.decks = decks;
	}

}
