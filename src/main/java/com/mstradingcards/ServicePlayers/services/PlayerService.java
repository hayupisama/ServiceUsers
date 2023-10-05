package com.mstradingcards.ServicePlayers.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.mstradingcards.ServicePlayers.dto.CartesDTO;
import com.mstradingcards.ServicePlayers.dto.DeckDTO;
import com.mstradingcards.ServicePlayers.dto.PlayerDTO;
import com.mstradingcards.ServicePlayers.models.Player;
import com.mstradingcards.ServicePlayers.repository.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private WebClient webClient;

	public Optional<PlayerDTO> findByUsername(String username) {
		return playerRepository.findByUsername(username).map(this::mapToPlayerDTO);
	}

	public Optional<PlayerDTO> findByEmail(String email) {
		return playerRepository.findByEmail(email).map(this::mapToPlayerDTO);
	}

	public List<PlayerDTO> searchPlayersByKeyword(String keyword) {
		List<Player> players = playerRepository.searchPlayersByKeyword(keyword);
		return players.stream().map(this::mapToPlayerDTO).collect(Collectors.toList());
	}

	public List<PlayerDTO> findAllPlayers() {
		List<Player> players = playerRepository.findAll();
		return players.stream().map(this::mapToPlayerDTO).collect(Collectors.toList());
	}

	/* Communication with DECKSERVICE */
	public List<DeckDTO> getAllPlayerDeck(Long playerId) {
		DeckDTO[] listDecks = webClient.get().uri("http://localhost:8081/api/decks/getAllDecks/" + playerId).retrieve()
				.bodyToMono(DeckDTO[].class).block();
		return Arrays.asList(listDecks);
	}

	public List<CartesDTO> getAllCards() {
		CartesDTO[] listCards = webClient.get().uri("http://localhost:8080/api/cards/getAllCards").retrieve()
				.bodyToMono(CartesDTO[].class).block();
		return Arrays.asList(listCards);
	}

	public PlayerDTO createPlayer(PlayerDTO playerDTO) {
		Player player = mapToPlayerEntity(playerDTO);
		return mapToPlayerDTO(playerRepository.save(player));
	}

	public DeckDTO createPlayerDeck(Long playerId, DeckDTO deckDTO) {
		return webClient.post().uri("http://localhost:8081/api/decks/createDeck/" + playerId)
				.body(BodyInserters.fromValue(deckDTO)).retrieve().bodyToMono(DeckDTO.class).block();
	}

	public DeckDTO updatePlayerDeck(Long userId, DeckDTO deckDTO) {
		return webClient.put().uri("http://localhost:8081/api/decks/updateDeck").body(BodyInserters.fromValue(deckDTO))
				.retrieve().bodyToMono(DeckDTO.class).block();
	}

	public void deletePlayerDeck(Long userId, String name) {
		webClient.delete().uri("http://localhost:8081/api/decks/deleteDeck/" + name).retrieve().bodyToMono(Void.class)
				.block();
	}

	/* Communication with DECKSERVICE */

	private PlayerDTO mapToPlayerDTO(Player player) {
		PlayerDTO playerDTO = new PlayerDTO();
		playerDTO.setPlayer_id(player.getId());
		playerDTO.setUsername(player.getUsername());
		playerDTO.setDecks(player.getDecks());
		playerDTO.setUser_id(player.getUser_id());
		playerDTO.setEmail(player.getEmail());
		return playerDTO;
	}

	private Player mapToPlayerEntity(PlayerDTO playerDTO) {
		Player player = new Player();
		player.setUser_id(playerDTO.getUser_id());
		player.setUsername(playerDTO.getUsername());
		player.setEmail(playerDTO.getEmail());
		return player;
	}
}
