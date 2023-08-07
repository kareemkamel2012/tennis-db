package com.example.accessingdata.Service;

import com.example.accessingdata.Player.Player;
import com.example.accessingdata.Repository.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {
    PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Player> addNewPlayer (String name, int ranking) {
        Player newPlayer = new Player();
        if (repository.existsByName(name) || repository.existsByRanking(ranking)) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else {
            newPlayer.setName(name);
            newPlayer.setRanking(ranking);
            repository.save(newPlayer);
            return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Iterable<Player>> getAllPlayers() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Player> getPlayerById(int id) {
        Optional<Player> playerById = repository.findById(id);
        return playerById
                .map(player -> new ResponseEntity<>(player, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
    public ResponseEntity<Player> getPlayerByName(String name) {
        Optional<Player> playerByName = repository.findByName(name);
        return playerByName
                .map(player -> new ResponseEntity<>(player, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
    public ResponseEntity<Player> getPlayerByRanking(String ranking) {
        try {
            Optional<Player> playerByRanking = repository.findByRanking(Integer.parseInt(ranking));
            return playerByRanking
                    .map(player -> new ResponseEntity<>(player, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        catch (NumberFormatException nfe) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Player> updatePlayer(int id, String name, int ranking) {
        Optional<Player> player = repository.findById(id);
        if(!player.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
//        if (repository.existsByName(name) || repository.existsByRanking(ranking)) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        } //todo: figure out the logic here
        Player playerToUpdate = player.get();
        playerToUpdate.setName(name);
        playerToUpdate.setRanking(ranking);
        repository.save(playerToUpdate);
        return new ResponseEntity<>(playerToUpdate, HttpStatus.OK);
    }

    public ResponseEntity<Void> deletePlayer(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
