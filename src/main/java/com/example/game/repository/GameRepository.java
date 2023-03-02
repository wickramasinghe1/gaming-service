package com.example.game.repository;

import com.example.game.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    @Query(value = "select * from game where is_active=true", nativeQuery = true)
    List<Game> getAllGames();
}
