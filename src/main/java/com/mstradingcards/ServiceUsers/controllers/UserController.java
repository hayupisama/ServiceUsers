package com.mstradingcards.ServiceUsers.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mstradingcards.ServiceUsers.dto.CartesDTO;
import com.mstradingcards.ServiceUsers.dto.DeckDTO;
import com.mstradingcards.ServiceUsers.dto.UserDTO;
import com.mstradingcards.ServiceUsers.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/getAllUsers")
	public List<UserDTO> getAllUsers() {
		return userService.findAllUsers();
	}

	@GetMapping("/username/{username}")
	public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
		Optional<UserDTO> user = userService.findByUsername(username);
		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
		Optional<UserDTO> user = userService.findByEmail(email);
		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/search/{keyword}")
	public List<UserDTO> searchUsersByKeyword(@PathVariable String keyword) {
		return userService.searchUsersByKeyword(keyword);
	}

	@GetMapping("/getAllCards")
	public List<CartesDTO> getAllCards() {
		return userService.getAllCards();
	}

	@GetMapping("/getAllDecks")
	public List<DeckDTO> getAllDecks() {
		// TODO GET PLAYER ID FROM JWT TOKEN
		Long playerId = -1L;
		return userService.getAllUserDeck(playerId);
	}

	@PostMapping("/createDeck")
	public DeckDTO createDeck(@RequestBody DeckDTO deckDTO) {
		// TODO GET PLAYER ID FROM JWT TOKEN
		Long playerId = -1L;
		return userService.createUserDeck(playerId, deckDTO);
	}

	@PutMapping("/updateDeck")
	public DeckDTO updateDeck(@RequestBody DeckDTO deckDTO) {
		// TODO GET PLAYER ID FROM JWT TOKEN
		Long playerId = -1L;
		return userService.updateUserDeck(playerId, deckDTO);
	}

	@DeleteMapping("/deleteDeck/{name}")
	public void deleteDeck(@PathVariable String name) {
		// TODO GET PLAYER ID FROM JWT TOKEN
		Long playerId = -1L;
		userService.deleteUserDeck(playerId, name);
	}

}
