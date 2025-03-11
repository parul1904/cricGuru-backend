package in.cricguru.controller;

import in.cricguru.dto.PlayerDto;
import in.cricguru.dto.VenueDto;
import in.cricguru.entity.Venue;
import in.cricguru.response.StatsPerPlayerResponse;
import in.cricguru.response.VenueStatsResponse;
import in.cricguru.service.StatsService;
import in.cricguru.service.VenueService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/venues")
public class VenueController {

    @Autowired
    private VenueService venueService;

    @Autowired
    private StatsService statsService;

    @GetMapping
    public ModelAndView getAllVenues() {
        ModelAndView modelAndView = new ModelAndView("user/venue");
        List<VenueDto> venues = venueService.getAllVenues();
        modelAndView.addObject("venues", venues);
        return modelAndView;
    }

    @GetMapping("/details/{venueId}")
    public ModelAndView venueStatsDetails(@PathVariable Long venueId) {
        ModelAndView modelAndView = new ModelAndView("user/venue-details");
        VenueDto venue = venueService.getVenueById(venueId);
        VenueStatsResponse statsDetails = statsService.getVenueStats(Math.toIntExact(venueId));
        modelAndView.addObject("venue", venue);
        modelAndView.addObject("statsDetails", statsDetails);
        return modelAndView;
    }

}