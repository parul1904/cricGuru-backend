package in.cricguru.controller;

import in.cricguru.dto.Dropdown;
import in.cricguru.service.impl.DropdownServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/dropdown")
public class DropdownController {

    @Autowired
    private DropdownServiceImpl dropdownService;

    @GetMapping("/seasons")
    public List<Dropdown> getSeasons() {
        return dropdownService.getSeasons();
    }

    @GetMapping("/teams")
    public List<Dropdown> getTeams() {
        return dropdownService.getTeams();
    }

    @GetMapping("/venues")
    public List<Dropdown> getVenues() {
        return dropdownService.getVenues();
    }

    @GetMapping("/players")
    public List<Dropdown> getPlayers() {
        return dropdownService.getPlayers();
    }

    @GetMapping("/matches/{seasonId}")
    public List<Dropdown> getMatches(@PathVariable  Integer seasonId) {
        return dropdownService.getMatches(seasonId);
    }

    @GetMapping("/matches")
    public List<Dropdown> getAllMatches() {
        return dropdownService.getAllMatches();
    }
}
