package in.cricguru.controller;

import in.cricguru.dto.PlayerDto;
import in.cricguru.dto.TeamDto;
import in.cricguru.service.PlayerService;
import in.cricguru.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommonController {

    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @GetMapping("/")
    public ModelAndView home() {
        logger.info("*** Home page requested ****");
        return new ModelAndView("redirect:/matches/fixture");
    }

/*    @GetMapping("/about")
    public ModelAndView about() {
        ModelAndView modelAndView = new ModelAndView("user/about");
        return modelAndView;
    }*/

    @GetMapping("/about")
    public String about() {
        logger.info("*** about Us page requested ****");
        //ModelAndView modelAndView = new ModelAndView("user/about");
        return "About US Page";
    }

    @GetMapping("/team")
    public String team(Model model) {
        return "team";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin/dashboard";
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
    "allPlayer", "allSeasons", "allSquads", "allTeams", "allStats"}, allEntries = true )
    public String evictCache() {
        return "Cache evicted";
    }
}
