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
        
        MatchDto matchDetails = matchService.getMatchById(matchNo);
        Integer team1Id = matchDetails.getTeam1Id();
        Integer team2Id = matchDetails.getTeam2Id();
        
        // Initialize all required variables
        List<PlayerPerformanceResponse> performanceData = new ArrayList<>();
        String performanceDataJson;
        
        if (matchNo <= 74) {
            // Season 2024 (Season 1)
            List<DreamTeamResponse> oldDreamTeam = dreamTeamService.getOldDreamTeamByMatchNo(matchNo);
            List<DreamTeamResponse> newDreamTeam = dreamTeamService.getNewDreamTeamByMatchNo(matchNo);
            List<DreamTeamResponse> my11CircleTeam = dreamTeamService.getMy11CircleDreamTeamByMatchNo(matchNo);
            performanceData = statsService.getPlayerPerformanceStats(matchNo);
            
            modelAndView.addObject("oldDreamTeamJson", objectMapper.writeValueAsString(oldDreamTeam));
            modelAndView.addObject("newDreamTeamJson", objectMapper.writeValueAsString(newDreamTeam));
            modelAndView.addObject("my11CirceTeamJson", objectMapper.writeValueAsString(my11CircleTeam));
            modelAndView.addObject("seasonYear", "2024");
        } else {
            // Season 2025 (Season 2)
            List<DreamTeamResponse> season2DreamTeam = statsService.getNewDreamTeamByMatchNo(2, team1Id, team2Id, 3);
            performanceData = statsService.getPlayerPerformanceData(2, team1Id, team2Id, 3);
            
            modelAndView.addObject("season2DreamTeamJson", objectMapper.writeValueAsString(season2DreamTeam));
            modelAndView.addObject("seasonYear", "2025");
        }
        
        modelAndView.addObject("matchDetails", matchDetails);
        modelAndView.addObject("performanceDataJson", objectMapper.writeValueAsString(performanceData));
        
        return modelAndView;
    }
}