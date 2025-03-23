package in.cricguru.service.impl;

import in.cricguru.mapper.DreamTeamMapper;
import in.cricguru.repository.StatsRepository;
import in.cricguru.response.DreamTeamResponse;
import in.cricguru.response.PlayerPerformanceResponse;
import in.cricguru.response.PlayerSelectionResponse;
import in.cricguru.service.DreamTeamService;
import in.cricguru.service.StatsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DreamTeamServiceImpl implements DreamTeamService {

    private final DreamTeamMapper dreamTeamMapper;
    private final StatsRepository statsRepository;

    public DreamTeamServiceImpl(DreamTeamMapper dreamTeamMapper,
                                StatsRepository statsRepository) {
        this.dreamTeamMapper = dreamTeamMapper;
        this.statsRepository = statsRepository;
    }

    @Override
    public List<DreamTeamResponse> getOldDreamTeamByMatchNo(Integer matchNo) {
        List<Object[]> dbResults = statsRepository.getOldDreamTeamByMatchNo(Long.valueOf(matchNo)).stream()
                .collect(Collectors.toList());
        return dreamTeamMapper.mapToDreamTeamResponse(dbResults);
    }

    @Override
    public List<DreamTeamResponse> getNewDreamTeamByMatchNo(Integer matchNo) {
        List<Object[]> dbResults = statsRepository.getNewDreamTeamByMatchNo(Long.valueOf(matchNo)).stream()
                .collect(Collectors.toList());
        return dreamTeamMapper.mapToDreamTeamResponse(dbResults);
    }

    @Override
    public List<DreamTeamResponse> getMy11CircleDreamTeamByMatchNo(Integer matchNo) {
        List<Object[]> dbResults = statsRepository.getMy11CircleDreamTeamByMatchNo(Long.valueOf(matchNo)).stream()
                .collect(Collectors.toList());
        return dreamTeamMapper.mapToDreamTeamResponse(dbResults);
    }

    @Override
    public List<PlayerSelectionResponse> getPlayerSelectionResponses(Integer team1Id, Integer team2Id) {
        List<Object[]> dbResults = statsRepository.getPlayerSelectionResponses(Long.valueOf(team1Id), Long.valueOf(team2Id)).stream()
                .collect(Collectors.toList());
        return dreamTeamMapper.mapToPlayerSelectionResponse(dbResults);
    }
}
