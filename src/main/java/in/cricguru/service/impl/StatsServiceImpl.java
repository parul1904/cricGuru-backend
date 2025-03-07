package in.cricguru.service.impl;

import in.cricguru.response.ListStatsResponse;
import in.cricguru.dto.StatsDto;
import in.cricguru.response.StatsPerMatchResponse;
import in.cricguru.response.StatsPerPlayerResponse;
import in.cricguru.entity.Stats;
import in.cricguru.mapper.StatsMapper;
import in.cricguru.repository.StatsRepository;
import in.cricguru.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
} 