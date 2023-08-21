package com.example.accessingdata.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.accessingdata.Player.Player;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {
    Optional<Player> findByRanking(Integer ranking);

    boolean existsByRanking(Integer ranking);

    boolean existsByName(String name);

    Optional<Player> findByName(String name);
}