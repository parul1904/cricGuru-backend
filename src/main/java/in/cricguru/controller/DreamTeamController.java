package in.cricguru.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.cricguru.dto.MatchDto;
import in.cricguru.response.DreamTeamResponse;
import in.cricguru.response.PlayerPerformanceResponse;
import in.cricguru.response.StatsPerMatchResponse;
import in.cricguru.service.DreamTeamService;
import in.cricguru.service.MatchService;
import in.cricguru.service.StatsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/dreamTeam")
public class DreamTeamController {

    @Autowired
    private ObjectMapper objectMapper;

    private final DreamTeamService dreamTeamService;

    private final StatsService statsService;

    private final MatchService matchService;

    @GetMapping("{matchNo}")
    public ModelAndView getDreamTeamByMatchNo(@PathVariable Integer matchNo) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("user/matchStats");
        MatchDto matchDetails = matchService.getMatchById(matchNo);
        Integer team1Id = matchDetails.getTeam1Id();
        Integer team2Id = matchDetails.getTeam2Id();
        List<DreamTeamResponse> oldDreamTeam = dreamTeamService.getOldDreamTeamByMatchNo(team1Id, team2Id);
        String oldDreamTeamJson = objectMapper.writeValueAsString(oldDreamTeam);
        List<DreamTeamResponse> newDreamTeam = dreamTeamService.getNewDreamTeamByMatchNo(team1Id, team2Id);
        String newDreamTeamJson = objectMapper.writeValueAsString(newDreamTeam);
        List<DreamTeamResponse> my11CircleTeam = dreamTeamService.getMy11CircleDreamTeamByMatchNo(team1Id, team2Id);
        String my11CirceTeamJson = objectMapper.writeValueAsString(my11CircleTeam);
        modelAndView.addObject("oldDreamTeamJson", oldDreamTeamJson);
        modelAndView.addObject("newDreamTeamJson", newDreamTeamJson);
        modelAndView.addObject("my11CirceTeamJson", my11CirceTeamJson);
        List<PlayerPerformanceResponse> performanceData = statsService.getPlayerPerformanceData(team1Id, team2Id);
        String performanceDataJson = objectMapper.writeValueAsString(performanceData);
        modelAndView.addObject("seasonYear", "2025");
        modelAndView.addObject("performanceDataJson", performanceDataJson);
        return modelAndView;
    }
}