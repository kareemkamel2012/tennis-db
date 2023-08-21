package com.example.accessingdata.Service;

import com.example.accessingdata.Player.Player;
import com.example.accessingdata.Repository.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@Service
public class PlayerService {
    PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public String viewPlayers(Model model) {
        model.addAttribute("playerList", getAllPlayers().getBody());
        return "viewPlayers/view";
    }

    public String addPlayerForm(Model model) {
        model.addAttribute("player", new Player());
        return "newPlayer/newPlayer";
    }

    public String addPlayerSubmit(@ModelAttribute Player player, Model model) {
        model.addAttribute("player", player);
        addNewPlayer(player);
        return "newPlayer/newPlayerResult";
    }

    public String updatePlayerForm(Model model) {
        model.addAttribute("player", new Player());
        return "updatePlayer/updatePlayer";
    }

    public String updatePlayerSubmit(@ModelAttribute Player player, Model model) {
        model.addAttribute("player", player);
        updatePlayer(player);
        return "updatePlayer/updatePlayerResult";
    }

    public String removePlayerForm(Model model) {
        model.addAttribute("player", new Player());
        return "removePlayer/removePlayer";
    }

    public String removePlayerSubmit(@ModelAttribute Player player, Model model) {
        model.addAttribute("player", player);
        deletePlayer(player.getId());
        return "removePlayer/removePlayerResult";
    }
    public ResponseEntity<Player> addNewPlayer (Player player) {
        if (repository.existsByName(player.getName()) || repository.existsByRanking(player.getRanking())) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else {
            repository.save(player);
            return new ResponseEntity<>(player, HttpStatus.CREATED);
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

    public ResponseEntity<Player> updatePlayer(Player player) {
        Optional<Player> playerById = repository.findById(player.getId());
        if(!playerById.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
//        if (repository.existsByName(name) || repository.existsByRanking(ranking)) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        } //todo: figure out the logic here
        repository.save(player);
        return new ResponseEntity<>(player, HttpStatus.OK);
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
