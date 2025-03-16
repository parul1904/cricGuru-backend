package in.cricguru.service;

import in.cricguru.dto.StatsDto;
import in.cricguru.response.*;

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

    List<PlayerPerformanceResponse> getPlayerPerformanceData(Integer team1Id, Integer team2Id);
} 