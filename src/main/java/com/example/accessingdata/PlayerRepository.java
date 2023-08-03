package com.example.accessingdata;

import org.springframework.data.repository.CrudRepository;

import com.example.accessingdata.Player;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PlayerRepository extends CrudRepository<Player, Integer> {
    Optional<Player> findByRanking(String ranking);

    boolean existsByName(String name);

    Optional<Player> findByName(String name);
}