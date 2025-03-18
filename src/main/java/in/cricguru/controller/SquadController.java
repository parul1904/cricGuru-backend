package in.cricguru.controller;
import in.cricguru.dto.SquadDto;
import in.cricguru.dto.TeamDto;
import in.cricguru.response.SquadResponse;
import in.cricguru.response.SquadTeamResponse;
import in.cricguru.service.SquadService;
import in.cricguru.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
public class SquadController {

    private SquadService squadService;

    private TeamService teamService;

    @GetMapping("/squads")
    public ModelAndView getAllSquads() {
        List<TeamDto> teams = teamService.getAllTeams();
        List<SquadResponse> squads = squadService.getAllSquads();
        ModelAndView modelAndView = new ModelAndView("user/squad");
        modelAndView.addObject("teams", teams);
        modelAndView.addObject("squads", squads);
        modelAndView.addObject("pageName", "Squad");
        return modelAndView;
    }

    @GetMapping("/team/{teamId}/squads")
    public ResponseEntity<SquadTeamResponse> getSquadsByTeam(@PathVariable("teamId") Long teamId) {
        SquadTeamResponse squads = squadService.getSquadDetailsByTeam(teamId);
        return ResponseEntity.ok(squads);
    }

    // build create Player REST API
    @PostMapping("/add-squad")
    public ResponseEntity<SquadDto> createSquad(@RequestBody SquadDto squadDto) {
        SquadDto SquadDto = squadService.createSquad(squadDto);
        return new ResponseEntity<>(SquadDto, HttpStatus.CREATED);
    }

    // build get Player by id REST API
    @GetMapping("{id}")
    public ResponseEntity<SquadDto> getSquadById(@PathVariable("id") Long squadId){
        SquadDto squad = squadService.getSquadById(squadId);
        return ResponseEntity.ok(squad);
    }

    // build update Player REST API
    @PutMapping("{id}")
    public ResponseEntity<SquadDto> updateSquad(@PathVariable("id") Long squadId,
                                                      @RequestBody SquadDto squadDto) {
        SquadDto updateSquad = squadService.updateSquad(squadId, squadDto);
        return ResponseEntity.ok(updateSquad);
    }

    // build delete Player REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSquad(@PathVariable("id") Long squadId){
        squadService.deleteSquad(squadId);
        return ResponseEntity.ok("Squad deleted successfully!");
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<SquadTeamResponse> getSquadDetailsByTeam(@PathVariable("teamId") Long teamId){
        SquadTeamResponse sqaudDetails= squadService.getSquadDetailsByTeam(teamId);
        return ResponseEntity.ok(sqaudDetails);
    }

}