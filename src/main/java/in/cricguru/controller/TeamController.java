package in.cricguru.controller;

import in.cricguru.dto.PlayerDto;
import in.cricguru.dto.TeamDto;
import in.cricguru.response.StatsPerPlayerResponse;
import in.cricguru.service.StatsService;
import in.cricguru.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/teams")
public class TeamController {

    private TeamService teamService;

    private final StatsService statsService;

    @GetMapping("/all")
    public ResponseEntity<List<TeamDto>> getAllTeams(){
        List<TeamDto> players = teamService.getAllTeams();
        return ResponseEntity.ok(players);
    }

    // build create Player REST API
    @PostMapping("/add-team")
    public ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto teamDto) {
        TeamDto TeamDto = teamService.createTeam(teamDto);
        return new ResponseEntity<>(TeamDto, HttpStatus.CREATED);
    }

    // build get Player by id REST API
    @GetMapping("{id}")
    public ResponseEntity<TeamDto> getTeamById(@PathVariable("id") Long teamId){
        TeamDto team = teamService.getTeamById(teamId);
        return ResponseEntity.ok(team);
    }

    @GetMapping("/compareTeams")
    public Map<String, Object> compareTeams(@RequestParam("team1") Long team1, @RequestParam("team2") Long team2){
        Map<String, Object> dataMap = new HashMap<>();
        TeamDto team1Details = teamService.getTeamById(team1);
        dataMap.put("team1Details", team1Details);
        StatsPerPlayerResponse statsDetails1 = statsService.getAllStatsByTeamId(Math.toIntExact(team1));
        dataMap.put("statsDetails1", statsDetails1);

        TeamDto team2Details = teamService.getTeamById(team2);
        dataMap.put("team2Details", team2Details);
        StatsPerPlayerResponse statsDetails2 = statsService.getAllStatsByTeamId(Math.toIntExact(team2));
        dataMap.put("statsDetails2", statsDetails2);
        System.out.println("player1Details details is:: "+statsDetails2);
        return dataMap;
    }

    // build update Player REST API
    @PutMapping("{id}")
    public ResponseEntity<TeamDto> updateTeam(@PathVariable("id") Long teamId,
                                                      @RequestBody TeamDto teamDto) {
        TeamDto updateTeam = teamService.updateTeam(teamId, teamDto);
        return ResponseEntity.ok(updateTeam);
    }

    // build delete Player REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable("id") Long teamId){
        teamService.deleteTeam(teamId);
        return ResponseEntity.ok("Team deleted successfully!");
    }
}