package com.mstradingcards.ServiceUsers.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mstradingcards.ServiceUsers.dto.UserDTO;
import com.mstradingcards.ServiceUsers.models.User;
import com.mstradingcards.ServiceUsers.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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



    private UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setDecks(user.getDecks());
        return userDTO;
    }
}
