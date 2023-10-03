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

	/* ADMIN ONLY */
	@GetMapping("/getAllPlayers")
	public List<PlayerDTO> getAllPlayers() {
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

	@PostMapping("/createPlayer")
	public PlayerDTO createDeck(@RequestBody PlayerDTO playerDTO) {
		return playerService.createPlayer(playerDTO);
	}
	/* ADMIN ONLY */

	@GetMapping("/getAllCards")
	public List<CartesDTO> getAllCards() {
		return playerService.getAllCards();
	}

	@GetMapping("/getAllDecks/{playerId}")
	public List<DeckDTO> getAllDecks(@PathVariable Long playerId) {
		return playerService.getAllPlayerDeck(playerId);
	}

	@PostMapping("/createDeck/{playerId}")
	public DeckDTO createDeck(@RequestBody DeckDTO deckDTO, @PathVariable Long playerId) {
		return playerService.createPlayerDeck(playerId, deckDTO);
	}

	@PutMapping("/updateDeck/{playerId}")
	public DeckDTO updateDeck(@RequestBody DeckDTO deckDTO, @PathVariable Long playerId) {
		return playerService.updatePlayerDeck(playerId, deckDTO);
	}

	@DeleteMapping("/deleteDeck/{name}/{playerId}")
	public void deleteDeck(@PathVariable String name, @PathVariable Long playerId) {
		playerService.deletePlayerDeck(playerId, name);
	}

}
