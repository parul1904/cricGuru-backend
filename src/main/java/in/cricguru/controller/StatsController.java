package in.cricguru.controller;

import in.cricguru.dto.StatsDto;
import in.cricguru.response.ListStatsResponse;
import in.cricguru.response.StatsPerMatchResponse;
import in.cricguru.response.StatsPerPlayerResponse;
import in.cricguru.service.StatsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;

    @PostMapping("/add-stats")
    public ResponseEntity<StatsDto> createStats(@RequestBody StatsDto statsDto) {
        return new ResponseEntity<>(statsService.createStats(statsDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatsDto> getStatsById(@PathVariable Integer id) {
        return ResponseEntity.ok(statsService.getStatsById(id));
    }

    @GetMapping()
    public ResponseEntity<List<ListStatsResponse>> getAllStats() {
        List<ListStatsResponse> stats = statsService.getAllStats();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/admin/all")
    public ModelAndView getAllStatsLoad() {
        List<ListStatsResponse> stats = statsService.getAllStats();
        ModelAndView modelAndView = new ModelAndView("admin/listStats");
        modelAndView.addObject("allStats", stats);
        return modelAndView;
    }


    @GetMapping("/match/{matchId}")
    public ResponseEntity<List<StatsPerMatchResponse>> getAllStatsByMatchId(@PathVariable Integer matchId) {
        List<StatsPerMatchResponse> stats = statsService.getAllStatsByMatchId(matchId);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/admin/update/{id}")
    public ModelAndView updateStats(@PathVariable Integer id) {
        StatsDto stats = statsService.getStatsById(id);
        ModelAndView modelAndView = new ModelAndView("admin/editStats");
        modelAndView.addObject("stats", stats);
        return modelAndView;
    }

    @PutMapping("/admin/edit/{id}")
    public ResponseEntity<StatsDto> updateStats(@PathVariable Integer id, @RequestBody StatsDto statsDto) {
        StatsDto stats = statsService.updateStats(id, statsDto);
        return ResponseEntity.ok(stats);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStats(@PathVariable Integer id) {
        statsService.deleteStats(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<StatsPerPlayerResponse> getPlayerStatsByPlayerId(@PathVariable Integer playerId) {
        StatsPerPlayerResponse stats = statsService.getPlayerStatsByPlayerId(playerId);
        return ResponseEntity.ok(stats);
    }
} 
