package com.mstradingcards.ServicePlayers.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PlayerDTO {

	@NotBlank(message = "Username is required")
	private String username;

	private List<Long> decks;

	@NotNull
	private Long user_id;

	@Email(message = "Please provide a valid email address")
	@NotBlank(message = "Email is required")
	private String email;

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

	public Long getUser_id() {
		return user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
