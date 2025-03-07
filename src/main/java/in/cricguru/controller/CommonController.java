package in.cricguru.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    @GetMapping("evict-cache")
    @CacheEvict(value = {"seasons", "teams", "venues", "players", "matches", "allMatches",
    "allPlayer", "allSeasons", "allSquads", "allTeams"}, allEntries = true )
    public String evictCache() {
        return "Cache evicted";
    }
}
