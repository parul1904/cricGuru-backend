package in.cricguru.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.cricguru.dto.MatchDto;
import in.cricguru.response.DreamTeamResponse;
import in.cricguru.response.PlayerPerformanceResponse;
import in.cricguru.response.PlayerSelectionResponse;
import in.cricguru.response.StatsPerMatchResponse;
import in.cricguru.service.DreamTeamService;
import in.cricguru.service.MatchService;
import in.cricguru.service.StatsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dreamTeam")
public class DreamTeamController {

    @Autowired
    private ObjectMapper objectMapper;

    private final DreamTeamService dreamTeamService;

    private final StatsService statsService;

    private final MatchService matchService;

    public DreamTeamController(DreamTeamService dreamTeamService, StatsService statsService, MatchService matchService) {
        this.dreamTeamService = dreamTeamService;
        this.statsService = statsService;
        this.matchService = matchService;
    }

    @ModelAttribute
    public void setResponseHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }



    @GetMapping("{matchNo}")
    public ModelAndView getDreamTeamByMatchNo(@PathVariable Integer matchNo) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView("user/matchStats");
        List<PlayerPerformanceResponse> performanceData = new ArrayList<>();
        List<DreamTeamResponse> oldDreamTeam = new ArrayList<>();
        String oldDreamTeamJson = "";
        List<DreamTeamResponse> newDreamTeam = new ArrayList<>();
        String newDreamTeamJson = "";
        List<DreamTeamResponse> my11CircleTeam = new ArrayList<>();
        String my11CirceTeamJson = "";
        String performanceDataJson = "";

        List<DreamTeamResponse> dreamAvgTeam = new ArrayList<>();
        String dreamAvgTeamJson = "";
        List<DreamTeamResponse> dreamLast5AvgTeam = new ArrayList<>();
        String dreamLast5AvgTeamJson = "";

        List<PlayerSelectionResponse> playerSelectionResponses = new ArrayList<>();
        String playerSelectionResponsesJson = "";

        MatchDto matchDetails = matchService.getMatchById(matchNo);
        Integer team1Id = matchDetails.getTeam1Id();
        Integer team2Id = matchDetails.getTeam2Id();
        if (matchNo <= 74) {
            oldDreamTeam = dreamTeamService.getOldDreamTeamByMatchNo(matchNo);
            oldDreamTeamJson = objectMapper.writeValueAsString(oldDreamTeam);
            newDreamTeam = dreamTeamService.getNewDreamTeamByMatchNo(matchNo);
            newDreamTeamJson = objectMapper.writeValueAsString(newDreamTeam);
            my11CircleTeam = dreamTeamService.getMy11CircleDreamTeamByMatchNo(matchNo);
            my11CirceTeamJson = objectMapper.writeValueAsString(my11CircleTeam);
            performanceData = statsService.getPlayerPerformanceStats(matchNo);
            performanceDataJson = objectMapper.writeValueAsString(performanceData);
            modelAndView.addObject("oldDreamTeamJson", oldDreamTeamJson);
            modelAndView.addObject("seasonYear", "2024");
        } else {
            oldDreamTeam = statsService.getOldDreamTeamByMatchNo(2, team1Id, team2Id, 1);
            oldDreamTeamJson = objectMapper.writeValueAsString(oldDreamTeam);
            newDreamTeam = statsService.getNewDreamTeamByMatchNo(2, team1Id, team2Id, 1);
            newDreamTeamJson = objectMapper.writeValueAsString(newDreamTeam);
            my11CircleTeam = statsService.getMy11CircleDreamTeamByMatchNo(2, team1Id, team2Id, 1);
            my11CirceTeamJson = objectMapper.writeValueAsString(my11CircleTeam);

            dreamAvgTeam = statsService.getDream11AverageDreamTeamResponse(2, team1Id, team2Id, 1);
            dreamAvgTeamJson = objectMapper.writeValueAsString(dreamAvgTeam);
            dreamLast5AvgTeam = statsService.getMy11CircleAverageDreamTeamResponse(2, team1Id, team2Id, 1);
            dreamLast5AvgTeamJson = objectMapper.writeValueAsString(dreamLast5AvgTeam);
            performanceData = statsService.getPlayerPerformanceData(2, team1Id, team2Id, 5);
            performanceDataJson = objectMapper.writeValueAsString(performanceData);

            playerSelectionResponses = dreamTeamService.getPlayerSelectionResponses(team1Id, team2Id);
            playerSelectionResponsesJson = objectMapper.writeValueAsString(playerSelectionResponses);
            modelAndView.addObject("seasonYear", "2025");
            modelAndView.addObject("oldDreamTeamJson", newDreamTeamJson);
        }

        modelAndView.addObject("newDreamTeamJson", newDreamTeamJson);
        modelAndView.addObject("my11CirceTeamJson", my11CirceTeamJson);
        modelAndView.addObject("dreamAvgTeamJson", dreamAvgTeamJson);
        modelAndView.addObject("matchDetails", matchDetails);
        modelAndView.addObject("playerSelectionResponsesJson", playerSelectionResponsesJson);
        modelAndView.addObject("performanceDataJson", performanceDataJson);
        return modelAndView;
    }
}