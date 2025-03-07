package in.cricguru.controller;

import in.cricguru.response.DreamTeamResponse;
import in.cricguru.service.DreamTeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/dreamTeam")
public class DreamTeamController {

    private final DreamTeamService dreamTeamService;

    @GetMapping("/match/{matchNo}")
    public ResponseEntity<List<DreamTeamResponse>> getDreamTeamByMatchNo(@PathVariable Integer matchNo) {
        return ResponseEntity.ok(dreamTeamService.getDreamTeamByMatchNo(matchNo));
    }
}