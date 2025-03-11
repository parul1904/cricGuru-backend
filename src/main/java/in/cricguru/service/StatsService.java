package in.cricguru.service;

import in.cricguru.dto.StatsDto;
import in.cricguru.response.ListStatsResponse;
import in.cricguru.response.StatsPerMatchResponse;
import in.cricguru.response.StatsPerPlayerResponse;
import in.cricguru.response.VenueStatsResponse;

import java.util.List;

public interface StatsService {
    StatsDto createStats(StatsDto statsDto);
    StatsDto getStatsById(Integer id);

    List<ListStatsResponse> getAllStats();

    List<StatsPerMatchResponse> getAllStatsByMatchId(Integer matchId);
    StatsDto updateStats(Integer id, StatsDto statsDto);
    void deleteStats(Integer id);

    StatsPerPlayerResponse getPlayerStatsByPlayerId(Integer playerId);

    StatsPerPlayerResponse getAllStatsByTeamId(Integer teamId);

    VenueStatsResponse getVenueStats(Integer venueId);
} 