package in.cricguru.controller;

import in.cricguru.dto.PlayerDto;
import in.cricguru.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static in.cricguru.shared.CricGuruConstant.BASE_URL;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/players")
@CrossOrigin(origins = BASE_URL)
public class PlayerController {

    private PlayerService playerService;

    @GetMapping
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
    @GetMapping("{id}")
    public ResponseEntity<PlayerDto> getPlayerById(@PathVariable("id") Long PlayerId){
        PlayerDto Player = playerService.getPlayerById(PlayerId);
        return ResponseEntity.ok(Player);
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