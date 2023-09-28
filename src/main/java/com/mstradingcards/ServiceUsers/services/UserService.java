package com.mstradingcards.ServiceUsers.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.mstradingcards.ServiceUsers.dto.CartesDTO;
import com.mstradingcards.ServiceUsers.dto.DeckDTO;
import com.mstradingcards.ServiceUsers.dto.UserDTO;
import com.mstradingcards.ServiceUsers.models.User;
import com.mstradingcards.ServiceUsers.repository.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private WebClient webClient;

	public Optional<UserDTO> findByUsername(String username) {
		return userRepository.findByUsername(username).map(this::mapToUserDTO);
	}

	public Optional<UserDTO> findByEmail(String email) {
		return userRepository.findByEmail(email).map(this::mapToUserDTO);
	}

	public List<UserDTO> searchUsersByKeyword(String keyword) {
		List<User> users = userRepository.searchUsersByKeyword(keyword);
		return users.stream().map(this::mapToUserDTO).collect(Collectors.toList());
	}

	public List<UserDTO> findAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(this::mapToUserDTO).collect(Collectors.toList());
	}

	/* Communication with DECKSERVICE */
	public List<DeckDTO> getAllUserDeck(Long userId) {
		DeckDTO[] listDecks = webClient.get().uri("http://localhost:8081/api/decks/getAllDecks").retrieve()
				.bodyToMono(DeckDTO[].class).block();
		return Arrays.asList(listDecks);
	}

	public List<CartesDTO> getAllCards() {
		CartesDTO [] listCards = webClient.get().uri("http://localhost:8080/api/cards/getAllCards").retrieve()
				.bodyToMono(CartesDTO[].class).block();
		return Arrays.asList(listCards);
	}

	public DeckDTO createUserDeck(Long userId, DeckDTO deckDTO) {
		return webClient.post().uri("http://localhost:8081/api/decks/createDeck").body(BodyInserters.fromValue(deckDTO))
				.retrieve().bodyToMono(DeckDTO.class).block();
	}

	public DeckDTO updateUserDeck(Long userId, DeckDTO deckDTO) {
		return webClient.put().uri("http://localhost:8081/api/decks/updateDeck").body(BodyInserters.fromValue(deckDTO))
				.retrieve().bodyToMono(DeckDTO.class).block();
	}

	public void deleteUserDeck(Long userId, String name) {
		webClient.delete().uri("http://localhost:8081/api/decks/deleteDeck/" + name).retrieve().bodyToMono(Void.class)
				.block();
	}

	/* Communication with DECKSERVICE */

	private UserDTO mapToUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(user.getUsername());
		userDTO.setDecks(user.getDecks());
		return userDTO;
	}
}
