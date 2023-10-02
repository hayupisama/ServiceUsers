package com.mstradingcards.ServicePlayers.controllers;

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

import com.mstradingcards.ServicePlayers.dto.CartesDTO;
import com.mstradingcards.ServicePlayers.dto.DeckDTO;
import com.mstradingcards.ServicePlayers.dto.PlayerDTO;
import com.mstradingcards.ServicePlayers.services.PlayerService;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@GetMapping("/getAllPLayers")
	public List<PlayerDTO> getAllUsers() {
		return playerService.findAllPlayers();
	}

	@GetMapping("/username/{username}")
	public ResponseEntity<PlayerDTO> getPlayerByUsername(@PathVariable String username) {
		Optional<PlayerDTO> player = playerService.findByUsername(username);
		return player.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<PlayerDTO> getPlayerByEmail(@PathVariable String email) {
		Optional<PlayerDTO> player = playerService.findByEmail(email);
		return player.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/search/{keyword}")
	public List<PlayerDTO> searchUsersByKeyword(@PathVariable String keyword) {
		return playerService.searchPlayersByKeyword(keyword);
	}

	@GetMapping("/getAllCards")
	public List<CartesDTO> getAllCards() {
		return playerService.getAllCards();
	}

	@GetMapping("/getAllDecks")
	public List<DeckDTO> getAllDecks() {
		// TODO GET PLAYER ID FROM JWT TOKEN
		Long playerId = -1L;
		return playerService.getAllPlayerDeck(playerId);
	}

	@PostMapping("/createDeck")
	public DeckDTO createDeck(@RequestBody DeckDTO deckDTO) {
		// TODO GET PLAYER ID FROM JWT TOKEN
		Long playerId = -1L;
		return playerService.createPlayerDeck(playerId, deckDTO);
	}

	@PutMapping("/updateDeck")
	public DeckDTO updateDeck(@RequestBody DeckDTO deckDTO) {
		// TODO GET PLAYER ID FROM JWT TOKEN
		Long playerId = -1L;
		return playerService.updatePlayerDeck(playerId, deckDTO);
	}

	@DeleteMapping("/deleteDeck/{name}")
	public void deleteDeck(@PathVariable String name) {
		// TODO GET PLAYER ID FROM JWT TOKEN
		Long playerId = -1L;
		playerService.deletePlayerDeck(playerId, name);
	}

}
