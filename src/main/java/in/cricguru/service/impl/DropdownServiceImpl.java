package in.cricguru.service.impl;

import in.cricguru.dto.Dropdown;
import in.cricguru.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DropdownServiceImpl {

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Cacheable("seasons")
    public List<Dropdown> getSeasons() {
        List<Dropdown> seasons = new ArrayList<>();
        seasonRepository.findAll().forEach(season -> {
            seasons.add(new Dropdown(season.getSeasonId(), String.valueOf(season.getYear())));
        });
        return seasons;
    }

    @Cacheable("teams")
    public List<Dropdown> getTeams() {
        List<Dropdown> teams = new ArrayList<>();
        teamRepository.findAll().forEach(team -> {
            teams.add(new Dropdown(team.getTeamId(), team.getTeamShortName()));
        });
        return teams;
    }

    @Cacheable("venues")
    public List<Dropdown> getVenues() {
        List<Dropdown> venues = new ArrayList<>();
        venueRepository.findAll().forEach(venue -> {
            venues.add(new Dropdown(venue.getVenueId(), venue.getVenueName()));
        });
        return venues;
    }

    @Cacheable("players")
    public List<Dropdown> getPlayers() {
        List<Dropdown> players = new ArrayList<>();
        playerRepository.findAll().forEach(player -> {
            players.add(new Dropdown(player.getPlayerId(), player.getPlayerName()));
        });
        return players;
    }

    @Cacheable(cacheNames="matches", key = "#seasonId")
    public List<Dropdown> getMatches(Integer seasonId) {
        List<Dropdown> matches = new ArrayList<>();
        matchRepository.findAllBySeasonId(Long.valueOf(seasonId)).forEach(match -> {
            matches.add(new Dropdown(match.getMatchId(), match.getMatchNo() + " : " +match.getTeam1().getTeamName() + " Vs " + match.getTeam2().getTeamName()));
        });
        return matches;
    }

    @Cacheable("allMatches")
    public List<Dropdown> getAllMatches() {
        List<Dropdown> matches = new ArrayList<>();
        matchRepository.findAll().forEach(match -> {
            matches.add(new Dropdown(match.getMatchId(), match.getMatchNo() + " : " + match.getTeam1().getTeamName() + " Vs " + match.getTeam2().getTeamName()));
        });
        return matches;
    }
}
