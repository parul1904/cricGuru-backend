package in.cricguru.controller;

import in.cricguru.response.MatchBetweenResponse;
import in.cricguru.dto.MatchDto;
import in.cricguru.response.MatchResponse;
import in.cricguru.service.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/matches")
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

    @GetMapping("/fixture")
    public ModelAndView getMatches() {
        ModelAndView modelAndView = new ModelAndView("user/fixture");
        // Get matches for 2024 by default
        List<MatchResponse> matches2024 = matchService.getAllMatchDetailsBySeasonId(2024);
        List<MatchResponse> matches2025 = matchService.getAllMatchDetailsBySeasonId(2025);
        modelAndView.addObject("matches2024", matches2024);
        modelAndView.addObject("matches2025", matches2025);
        modelAndView.addObject("defaultSeason", "2024"); // Add this to help JavaScript know the default season
        return modelAndView;
    }

    @GetMapping("/matches")
    @ResponseBody
    public ResponseEntity<List<MatchResponse>> getAllMatchDetailsBySeason(@RequestParam Integer seasonYear) {
        try {
            List<MatchResponse> matches = matchService.getAllMatchDetailsBySeasonId(seasonYear);
            return ResponseEntity.ok(matches);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @GetMapping("/matchcentre")
    public ModelAndView getMatchCentre(@RequestParam Integer matchId, Model model) {
        ModelAndView modelAndView = new ModelAndView("user/matchcentre");
        MatchDto match = matchService.getMatchById(matchId);
        model.addAttribute("match", match);
        return modelAndView;
    }
} 