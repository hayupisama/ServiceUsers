package com.mstradingcards.ServicePlayers.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mstradingcards.ServicePlayers.models.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByUsername(String username);

    Optional<Player> findByEmail(String email);

    @Query("SELECT u FROM Player u WHERE u.username LIKE %:keyword% OR u.email LIKE %:keyword%")
    List<Player> searchPlayersByKeyword(String keyword);

}

