package in.cricguru.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    @GetMapping("/")
    public String login(Model model) {
        return "index";
    }

    @GetMapping("/team")
    public String team(Model model) {
        return "team";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin/dashboard";
    }

    @GetMapping("evict-cache")
    @CacheEvict(value = {"seasons", "teams", "venues", "players", "matches", "allMatches",
    "allPlayer", "allSeasons", "allSquads", "allTeams", "allStats"}, allEntries = true )
    public String evictCache() {
        return "Cache evicted";
    }
}
