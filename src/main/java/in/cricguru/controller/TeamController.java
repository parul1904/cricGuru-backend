package in.cricguru.controller;

import in.cricguru.dto.TeamDto;
import in.cricguru.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/teams")
@CrossOrigin(origins = "http://43.204.102.164:3000")
public class TeamController {

    private TeamService teamService;

    @GetMapping
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