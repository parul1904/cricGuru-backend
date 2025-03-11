package in.cricguru.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.cricguru.response.DreamTeamResponse;
import in.cricguru.response.StatsPerMatchResponse;
import in.cricguru.service.DreamTeamService;
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
@RequestMapping("/api/v1/dreamTeam")
public class DreamTeamController {

    @Autowired
    private ObjectMapper objectMapper;

    private final DreamTeamService dreamTeamService;

    private final StatsService statsService;

    @GetMapping("{matchNo}")
    public ModelAndView getDreamTeamByMatchNo(@PathVariable Integer matchNo) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("user/matchStats");
        List<DreamTeamResponse> oldDreamTeam = dreamTeamService.getOldDreamTeamByMatchNo(matchNo);
        String oldDreamTeamJson = objectMapper.writeValueAsString(oldDreamTeam);
        List<DreamTeamResponse> newDreamTeam = dreamTeamService.getNewDreamTeamByMatchNo(matchNo);
        String newDreamTeamJson = objectMapper.writeValueAsString(newDreamTeam);
        List<DreamTeamResponse> my11CircleTeam = dreamTeamService.getMy11CircleDreamTeamByMatchNo(matchNo);
        String my11CirceTeamJson = objectMapper.writeValueAsString(my11CircleTeam);
        modelAndView.addObject("oldDreamTeamJson", oldDreamTeamJson);
        modelAndView.addObject("newDreamTeamJson", newDreamTeamJson);
        modelAndView.addObject("my11CirceTeamJson", my11CirceTeamJson);
        List<StatsPerMatchResponse> matchStats = statsService.getAllStatsByMatchId(matchNo);
        String matchStatsTeamJson = objectMapper.writeValueAsString(matchStats);
        modelAndView.addObject("matchStats", matchStatsTeamJson);
        return modelAndView;
    }
}