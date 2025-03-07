package in.cricguru.controller;

import in.cricguru.response.MatchBetweenResponse;
import in.cricguru.dto.MatchDto;
import in.cricguru.response.MatchResponse;
import in.cricguru.service.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/matches")
public class MatchController {

    private final MatchService matchService;

    @PostMapping("/add-match")
    public ResponseEntity<MatchDto> createMatch(@RequestBody MatchDto matchDto) {
        return new ResponseEntity<>(matchService.createMatch(matchDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDto> getMatchById(@PathVariable Integer id) {
        return ResponseEntity.ok(matchService.getMatchById(id));
    }

    @GetMapping
    public ResponseEntity<List<MatchResponse>> getAllMatches() {
        List<MatchResponse> matches = matchService.getAllMatches();
        return ResponseEntity.ok(matches);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchDto> updateMatch(@PathVariable Integer id, @RequestBody MatchDto matchDto) {
        return ResponseEntity.ok(matchService.updateMatch(id, matchDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Integer id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/matchId/{matchId}")
    public ResponseEntity<List<MatchBetweenResponse>> getMatchDetailsByMatchId(@PathVariable Integer matchId) {
        return ResponseEntity.ok(matchService.getMatchDetailsByMatchId(matchId));
    }
} 