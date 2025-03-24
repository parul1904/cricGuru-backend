package in.cricguru.controller;

import in.cricguru.dto.DreamPlayerTeamDto;
import in.cricguru.service.DreamPlayerTeamService;
import in.cricguru.service.SquadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/dream-player-team")
@RequiredArgsConstructor
public class DreamPlayerTeamController {

    private final DreamPlayerTeamService dreamPlayerTeamService;
    private final SquadService squadService;

    @GetMapping
    public ModelAndView updateDreamTeamPage() {
        ModelAndView modelAndView = new ModelAndView("admin/dreamPlayerTeamManagement");
        return modelAndView;
    }

    @PostMapping("/batch")
    public ResponseEntity<Void> saveBatch(@RequestBody List<DreamPlayerTeamDto> dreamPlayerTeams) {
        dreamPlayerTeamService.saveBatch(dreamPlayerTeams);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/match/{matchNo}")
    public ResponseEntity<List<DreamPlayerTeamDto>> getByMatch(@PathVariable Integer matchNo) {
        return ResponseEntity.ok(dreamPlayerTeamService.getByMatch(matchNo));
    }

    @GetMapping("/squad")
    public ResponseEntity<List<DreamPlayerTeamDto>> getSquadPlayers(
            @RequestParam Long seasonId,
            @RequestParam Long team1Id,
            @RequestParam Long team2Id) {
        return ResponseEntity.ok(squadService.getSquadPlayersByTeams( team1Id, team2Id));
    }
}
