package in.cricguru.controller;

import in.cricguru.dto.PlayerDto;
import in.cricguru.dto.TeamDto;
import in.cricguru.response.MatchResponse;
import in.cricguru.service.MatchService;
import in.cricguru.service.PlayerService;
import in.cricguru.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;

@RestController
public class CommonController {

    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MatchService matchService;

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");

        // Get live/upcoming matches
        List<MatchResponse> upcomingMatches = matchService.getUpcomingMatches(3);
        modelAndView.addObject("liveMatches", upcomingMatches);

        MatchResponse nextMatch = matchService.nextMatch();
        modelAndView.addObject("nextMatch", nextMatch);

        // Add stats for counter section
        Map<String, Object> stats = new HashMap<>();
        stats.put("activeUsers", "50K+");
        stats.put("predictionAccuracy", "95%");
        stats.put("matchesAnalyzed", "74");
        stats.put("expertAnalysts", "10+");
        modelAndView.addObject("stats", stats);

        // Set page name for header highlighting
        modelAndView.addObject("pageName", "index");

        return modelAndView;
    }

    @GetMapping("/about")
    public ModelAndView about() {
        ModelAndView modelAndView = new ModelAndView("user/about");
        return modelAndView;
    }

    @GetMapping("/guide")
    public ModelAndView guide() {
        ModelAndView modelAndView = new ModelAndView("user/guide");
        return modelAndView;
    }

    @GetMapping("/privacy")
    public ModelAndView privacy() {
        ModelAndView modelAndView = new ModelAndView("user/privacy");
        return modelAndView;
    }
    @GetMapping("/terms")
    public ModelAndView terms() {
        ModelAndView modelAndView = new ModelAndView("user/terms");
        return modelAndView;
    }


    @GetMapping("/team")
    public String team(Model model) {
        return "team";
    }

    @GetMapping("/admin/cricguru/add-stats")
    public ModelAndView addStats(Model model) {
        ModelAndView modelAndView = new ModelAndView("admin/addstats");
        return modelAndView;
    }

    @GetMapping("/compare")
    public ModelAndView comparePlayer(){
        ModelAndView modelAndView = new ModelAndView("user/compare");
        List<PlayerDto> players = playerService.getAllPlayers();
        modelAndView.addObject("players", players);
        List<TeamDto> teams = teamService.getAllTeams().stream().limit(10).collect(Collectors.toUnmodifiableList());
        modelAndView.addObject("teams", teams);
        return modelAndView;
    }

    @GetMapping("evict-cache")
    @CacheEvict(value = {"seasons", "teams", "venues", "players", "matches", "allMatches",
    "allPlayer", "allSeasons", "allSquads", "allTeams", "allStats", "allMatchesBySeason", "allMatches",
    "squadDetailsByTeam","playerDetailsById"}, allEntries = true )
    public String evictCache() {
        return "Cache evicted";
    }
}
