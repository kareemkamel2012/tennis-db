package com.example.accessingdata.Controller;

import com.example.accessingdata.Player.Player;
import com.example.accessingdata.Service.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
//@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
    private PlayerService service;

    public MainController(PlayerService service) {
        this.service = service;
    }

    @GetMapping("/new")
    public String addPlayerForm(Model model) {
        return service.addPlayerForm(model);
    }

    @PostMapping("/new")
    public String addPlayerSubmit(@ModelAttribute Player player, Model model) {
        return service.addPlayerSubmit(player, model);
    }

    @GetMapping("/modify")
    public String updatePlayerForm(Model model) {
        return service.updatePlayerForm(model);
    }

    @PostMapping("/modify")
    public String updatePlayerSubmit(@ModelAttribute Player player, Model model) {
        return service.updatePlayerSubmit(player, model);
    }

    @GetMapping("/remove")
    public String removePlayerForm(Model model) {
        return service.removePlayerForm(model);
    }

    @PostMapping("/remove")
    public String removePlayerSubmit(@ModelAttribute Player player, Model model) {
        return service.removePlayerSubmit(player, model);
    }

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody ResponseEntity<Player> addNewPlayer (@RequestParam String name
            , @RequestParam Integer ranking) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        return service.addNewPlayer(name, ranking);
    }

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<Iterable<Player>> getAllPlayers() {
        // This returns a JSON or XML with the players
        return service.getAllPlayers();
    }

    @GetMapping(path="/get/{id}")
    public @ResponseBody ResponseEntity<Player> getPlayerById(@PathVariable("id") Integer pathID) {
        // This returns a JSON or XML with the player matching the id if there is one
        return service.getPlayerById(pathID);
    }

    @GetMapping(path="/name/{name}")
    public @ResponseBody ResponseEntity<Player> getPlayerByName(@PathVariable("name") String name) {
        // This returns a JSON or XML with the player matching the id if there is one
        return service.getPlayerByName(name);
    }

    @GetMapping(path="/ranking/{ranking}")
    public @ResponseBody ResponseEntity<Player> getPlayerByRanking(@PathVariable("ranking") String ranking) {
        // This returns a JSON or XML with the player matching the ranking if there is one
        return service.getPlayerByRanking(ranking);
    }

    @PutMapping(path="/update")
    public @ResponseBody ResponseEntity<Player> updatePlayer (@RequestParam Integer id, @RequestParam String name
            , @RequestParam Integer ranking) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        return service.updatePlayer(id, name, ranking);
    }

    @DeleteMapping(path="/delete")
    public @ResponseBody ResponseEntity<Void> deletePlayer (@RequestParam Integer id) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        return service.deletePlayer(id);
    }
}