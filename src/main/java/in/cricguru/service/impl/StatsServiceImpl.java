package in.cricguru.service.impl;

import in.cricguru.response.*;
import in.cricguru.dto.StatsDto;
import in.cricguru.entity.Stats;
import in.cricguru.mapper.StatsMapper;
import in.cricguru.repository.StatsRepository;
import in.cricguru.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final StatsRepository statsRepository;
    private final StatsMapper statsMapper;

    @Override
    public StatsDto createStats(StatsDto statsDto) {
        Stats stats = statsMapper.mapToStats(statsDto);
        Stats savedStats = statsRepository.save(stats);
        return statsMapper.mapToStatsDto(savedStats);
    }


    @Override
    public StatsDto getStatsById(Integer id) {
        Stats stats = statsRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Stats not found with id: " + id));
        return statsMapper.mapToStatsDto(stats);
    }


   // @Cacheable(cacheNames="allStats")
    @Override
    public List<ListStatsResponse> getAllStats() {
        List<Object[]> statsDtos = statsRepository.getAllStats().stream()
                .collect(Collectors.toList());
        return statsMapper.mapToStatsResponseList(statsDtos);
    }

    @Override
    public List<StatsPerMatchResponse> getAllStatsByMatchId(Integer matchId) {
        List<Object[]> statsDtos = statsRepository.getAllStatsByMatchId(Long.valueOf(matchId)).stream()
                .collect(Collectors.toList());
        return statsMapper.mapToStatsPerMatchResponseList(statsDtos);
    }

    @Override
    public StatsDto updateStats(Integer id, StatsDto statsDto) {
        if (!statsRepository.existsById(Long.valueOf(id))) {
            throw new RuntimeException("Stats not found with id: " + id);
        }
        Stats existingStats = statsRepository.findById(Long.valueOf(id)).orElseThrow();
        Stats updatedStats = statsMapper.mapToStats(statsDto);
        updatedStats.setMatchStatsId(existingStats.getMatchStatsId());
        Stats savedStats = statsRepository.save(updatedStats);
        return statsMapper.mapToStatsDto(savedStats);
    }

    @Override
    public void deleteStats(Integer id) {
        if (!statsRepository.existsById(Long.valueOf(id))) {
            throw new RuntimeException("Stats not found with id: " + id);
        }
        statsRepository.deleteById(Long.valueOf(id));
    }

    @Override
    public StatsPerPlayerResponse getPlayerStatsByPlayerId(Integer playerId) {
        List<Object[]> statsDtos = statsRepository.getPlayerStatsByPlayerId(Long.valueOf(playerId)).stream()
                .collect(Collectors.toList());
            return statsMapper.mapToStatsPerPlayerResponseList(statsDtos);
    }

    @Override
    public StatsPerPlayerResponse getAllStatsByTeamId(Integer teamId) {
        List<Object[]> statsDtos = statsRepository.getAllStatsByTeamId(Long.valueOf(teamId)).stream()
                .collect(Collectors.toList());
        return statsMapper.mapToStatsPerTeamResponseList(statsDtos);
    }

    @Override
    public VenueStatsResponse getVenueStats(Integer venueId) {
        List<Object[]> statsDtos = statsRepository.getAllStatsByVenue(Long.valueOf(venueId)).stream()
                .collect(Collectors.toList());
        return statsMapper.mapToStatsPerVenue(statsDtos);
    }

    @Override
    public List<StatsPerMatchResponse> getOldDreamTeamByMatchNo(Integer matchNo) {
        List<Object[]> statsDtos = statsRepository.getOldDreamTeamByMatchNo(Long.valueOf(matchNo)).stream()
                .collect(Collectors.toList());
        return statsMapper.mapToStatsPerMatchResponseList(statsDtos);
    }

    @Override
    public List<PlayerPerformanceResponse> getPlayerPerformanceData(Integer seasonId, Integer team1Id, Integer team2Id, Integer statsBy) {
        List<Object[]> performanceData = statsRepository.getPlayerPerformanceStats(Long.valueOf(seasonId), Long.valueOf(team1Id), Long.valueOf(team2Id),
                        Long.valueOf(statsBy)).stream()
                .collect(Collectors.toUnmodifiableList());
        return statsMapper.mapToPlayerPerformanceResponse(performanceData, seasonId);
    }

    @Override
    public List<PlayerPerformanceResponse> getPlayerPerformanceStats(Integer matchNo) {
        List<Object[]> performanceData = statsRepository.getPlayerPerformanceStats(Long.valueOf(matchNo)).stream()
                .collect(Collectors.toUnmodifiableList());
        return statsMapper.mapToPlayerPerformanceResponse(performanceData, null);
    }

    @Override
    public List<DreamTeamResponse> getOldDreamTeamByMatchNo(Integer seasonId, Integer team1Id, Integer team2Id, Integer statsBy) {
        List<Object[]> performanceData = statsRepository.getPlayerPerformanceStats(Long.valueOf(seasonId), Long.valueOf(team1Id), Long.valueOf(team2Id),
                        Long.valueOf(statsBy)).stream()
                .collect(Collectors.toUnmodifiableList());
        return statsMapper.mapToOldPointDreamTeamResponse(performanceData);
    }

    @Override
    public List<DreamTeamResponse> getNewDreamTeamByMatchNo(Integer seasonId, Integer team1Id, Integer team2Id, Integer statsBy) {
        List<Object[]> performanceData = statsRepository.getPlayerPerformanceStats(Long.valueOf(seasonId), Long.valueOf(team1Id), Long.valueOf(team2Id),
                        Long.valueOf(statsBy)).stream()
                .collect(Collectors.toUnmodifiableList());
        return statsMapper.mapToNewPointDreamTeamResponse(performanceData);
    }

    @Override
    public List<DreamTeamResponse> getMy11CircleDreamTeamByMatchNo(Integer seasonId, Integer team1Id, Integer team2Id, Integer statsBy) {
        List<Object[]> performanceData = statsRepository.getPlayerPerformanceStats(Long.valueOf(seasonId), Long.valueOf(team1Id), Long.valueOf(team2Id),
                        Long.valueOf(statsBy)).stream()
                .collect(Collectors.toUnmodifiableList());
        return statsMapper.mapToMy11CirclePointDreamTeamResponse(performanceData);
    }

    @Override
    public List<DreamTeamResponse> getDream11AverageDreamTeamResponse(Integer seasonId, Integer team1Id, Integer team2Id, Integer statsBy) {
        List<Object[]> performanceData = statsRepository.getPlayerPerformanceStats(Long.valueOf(seasonId), Long.valueOf(team1Id), Long.valueOf(team2Id),
                        Long.valueOf(statsBy)).stream()
                .collect(Collectors.toUnmodifiableList());
        return statsMapper.mapToDream11AverageDreamTeamResponse(performanceData);
    }

    @Override
    public List<DreamTeamResponse> getMy11CircleAverageDreamTeamResponse(Integer seasonId, Integer team1Id, Integer team2Id, Integer statsBy) {
        List<Object[]> performanceData = statsRepository.getPlayerPerformanceStats(Long.valueOf(seasonId), Long.valueOf(team1Id), Long.valueOf(team2Id),
                        Long.valueOf(statsBy)).stream()
                .collect(Collectors.toUnmodifiableList());
        return statsMapper.mapToMy11CircleAverageDreamTeamResponse(performanceData);
    }

}