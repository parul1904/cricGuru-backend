package in.cricguru.controller;

import in.cricguru.dto.DreamPlayerTeamDto;
import in.cricguru.dto.PlayerRoleUpdate;
import in.cricguru.service.DreamPlayerTeamService;
import in.cricguru.service.SquadService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import lombok.Data;

@RestController
@RequestMapping("/dream-player-team")
@RequiredArgsConstructor
public class DreamPlayerTeamController {

    private final DreamPlayerTeamService dreamPlayerTeamService;
    private final SquadService squadService;
    private final Logger logger = LoggerFactory.getLogger(DreamPlayerTeamController.class);

    @GetMapping("/management")
    public ModelAndView updateDreamTeamPage() {
        ModelAndView modelAndView = new ModelAndView("admin/dreamPlayerTeamManagement");
        return modelAndView;
    }

    @PostMapping("/batch")
    public ResponseEntity<?> saveBatch(@RequestBody List<DreamPlayerTeamDto> dreamPlayerTeams) {
        try {
            dreamPlayerTeamService.saveBatch(dreamPlayerTeams);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            logger.error("Invalid request data", e);
            return ResponseEntity
                .badRequest()
                .body(new ErrorResponse("Invalid request data: " + e.getMessage()));
        } catch (RuntimeException e) {
            logger.error("Failed to save dream player teams", e);
            return ResponseEntity
                .internalServerError()
                .body(new ErrorResponse("Failed to save dream player teams: " + e.getMessage()));
        }
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
        return ResponseEntity.ok(squadService.getSquadPlayersByTeams(team1Id, team2Id));
    }

    @PostMapping("/update-roles")
    public ResponseEntity<Void> updatePlayerRoles(@RequestBody List<PlayerRoleUpdate> updates) {
        dreamPlayerTeamService.updatePlayerRoles(updates);
        return ResponseEntity.ok().build();
    }
}

@Data
@AllArgsConstructor
class ErrorResponse {
    private String message;
}
