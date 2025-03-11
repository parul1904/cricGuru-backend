package in.cricguru.controller;

import in.cricguru.dto.PlayerDto;
import in.cricguru.dto.TeamDto;
import in.cricguru.response.StatsPerPlayerResponse;
import in.cricguru.service.PlayerService;
import in.cricguru.service.StatsService;
import in.cricguru.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static in.cricguru.shared.CricGuruConstant.BASE_URL;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {

    private PlayerService playerService;

    private final StatsService statsService;

    private final TeamService teamService;

    @GetMapping("/all")
    public ResponseEntity<List<PlayerDto>> getAllPlayers(){
        List<PlayerDto> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    // build create Player REST API
    @PostMapping("/add-player")
    public ResponseEntity<PlayerDto> createPlayer(@RequestBody PlayerDto Player) {
        PlayerDto PlayerDto = playerService.createPlayer(Player);
        return new ResponseEntity<>(PlayerDto, HttpStatus.CREATED);
    }

    // build get Player by id REST API
    @GetMapping
    public ModelAndView getPlayerById(@RequestParam("playerId") Long playerId){
        PlayerDto playerDetails = playerService.getPlayerById(playerId);
        ModelAndView modelAndView = new ModelAndView("user/playerProfile");
        modelAndView.addObject("playerDetails", playerDetails);
        StatsPerPlayerResponse statsDetails = statsService.getPlayerStatsByPlayerId(Math.toIntExact(playerId));
        modelAndView.addObject("statsDetails", statsDetails);
        return modelAndView;
    }

    @GetMapping("/compare")
    public ModelAndView comparePlayer(){
        ModelAndView modelAndView = new ModelAndView("user/compare");
        List<PlayerDto> players = playerService.getAllPlayers();
        modelAndView.addObject("players", players);
        List<TeamDto> teams = teamService.getAllTeams().stream().limit(10).collect(Collectors.toUnmodifiableList());
        modelAndView.addObject("teams", teams);
        return modelAndView;
    }

    @GetMapping("/comparePlayers")
    public Map<String, Object> comparePlayers(@RequestParam("player1") Long player1, @RequestParam("player2") Long player2){
        Map<String, Object> dataMap = new HashMap<>();
        PlayerDto player1Details = playerService.getPlayerById(player1);
        dataMap.put("player1Details", player1Details);
        StatsPerPlayerResponse statsDetails1 = statsService.getPlayerStatsByPlayerId(Math.toIntExact(player1));
        dataMap.put("statsDetails1", statsDetails1);

        PlayerDto player2Details = playerService.getPlayerById(player2);
        dataMap.put("player2Details", player2Details);
        StatsPerPlayerResponse statsDetails2 = statsService.getPlayerStatsByPlayerId(Math.toIntExact(player2));
        dataMap.put("statsDetails2", statsDetails2);
        System.out.println("player1Details details is:: "+player1Details);
        return dataMap;
    }


    // build update Player REST API
    @PutMapping("{id}")
    public ResponseEntity<PlayerDto> updatePlayer(@PathVariable("id") Long PlayerId,
                                                      @RequestBody PlayerDto PlayerDetails) {
        PlayerDto updatePlayer = playerService.updatePlayer(PlayerId, PlayerDetails);
        return ResponseEntity.ok(updatePlayer);
    }

    // build delete Player REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable("id") Long PlayerId){
        playerService.deletePlayer(PlayerId);
        return ResponseEntity.ok("Player deleted successfully!");
    }
}