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
import java.util.Map;

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
        String performanceDataJson = "";

        List<DreamTeamResponse> oldDreamTeam = new ArrayList<>();
        String oldDreamTeamJson = "";
        List<DreamTeamResponse> lastMatchDreamTeam = new ArrayList<>();
        String lastMatchDreamTeamJson = "";
        List<DreamTeamResponse> last3MatchDreamTeam = new ArrayList<>();
        String last3MatchDreamTeamJson = "";
        List<DreamTeamResponse> last5MatchDreamTeam = new ArrayList<>();
        String last5MatchDreamTeamJson = "";
        List<DreamTeamResponse> actualDreamTeam = new ArrayList<>();
        String actualDreamTeamJson = "";

        List<PlayerSelectionResponse> playerSelectionResponses = new ArrayList<>();
        String playerSelectionResponsesJson = "";

        MatchDto matchDetails = matchService.getMatchById(matchNo);
        Integer team1Id = matchDetails.getTeam1Id();
        Integer team2Id = matchDetails.getTeam2Id();
        if (matchNo <= 74) {
            oldDreamTeam = dreamTeamService.getOldDreamTeamByMatchNo(matchNo);
            oldDreamTeamJson = objectMapper.writeValueAsString(oldDreamTeam);
            lastMatchDreamTeam = dreamTeamService.getNewDreamTeamByMatchNo(matchNo);
            lastMatchDreamTeamJson = objectMapper.writeValueAsString(lastMatchDreamTeam);
            performanceData = statsService.getPlayerPerformanceStats(matchNo);
            performanceDataJson = objectMapper.writeValueAsString(performanceData);
            modelAndView.addObject("oldDreamTeamJson", oldDreamTeamJson);
            modelAndView.addObject("seasonYear", "2024");
        } else {
            lastMatchDreamTeam = statsService.lastMatchDreamTeam(2, team1Id, team2Id, 75);
            lastMatchDreamTeamJson = objectMapper.writeValueAsString(lastMatchDreamTeam);

            last3MatchDreamTeam = statsService.last3MatchDreamTeam(2, team1Id, team2Id, 75);
            last3MatchDreamTeamJson = objectMapper.writeValueAsString(last3MatchDreamTeam);
            last5MatchDreamTeam = statsService.last5MatchDreamTeam(2, team1Id, team2Id, 75);
            last5MatchDreamTeamJson = objectMapper.writeValueAsString(last5MatchDreamTeam);

            performanceData = statsService.getPlayerPerformanceData(2, team1Id, team2Id, 5);
            performanceDataJson = objectMapper.writeValueAsString(performanceData);

            playerSelectionResponses = dreamTeamService.getPlayerSelectionResponses(Long.valueOf(matchNo));
            playerSelectionResponsesJson = objectMapper.writeValueAsString(playerSelectionResponses);
            System.out.println("playerSelectionResponsesJson Is: " + playerSelectionResponsesJson);
            modelAndView.addObject("seasonYear", "2025");
        }

         actualDreamTeam = dreamTeamService.getActualDreamTeamByMatchNo(matchNo);
        actualDreamTeamJson = objectMapper.writeValueAsString(actualDreamTeam);

        List<Integer> matchesWithDreamTeam = dreamTeamService.getMatchesWithDreamTeam();
        // Convert the List to JSON string for JSP
        String matchesWithDreamTeamJson = objectMapper.writeValueAsString(matchesWithDreamTeam);

        modelAndView.addObject("matchesWithDreamTeam", matchesWithDreamTeamJson);
        modelAndView.addObject("actualDreamTeamJson", actualDreamTeamJson);
        modelAndView.addObject("match", Map.of("matchNo", matchNo));

        modelAndView.addObject("lastMatchDreamTeamJson", lastMatchDreamTeamJson);
        modelAndView.addObject("last3MatchDreamTeamJson", last3MatchDreamTeamJson);
        modelAndView.addObject("last5MatchDreamTeamJson", last5MatchDreamTeamJson);
        modelAndView.addObject("matchDetails", matchDetails);
        modelAndView.addObject("playerSelectionResponsesJson", playerSelectionResponsesJson);
        modelAndView.addObject("performanceDataJson", performanceDataJson);
        return modelAndView;
    }
}