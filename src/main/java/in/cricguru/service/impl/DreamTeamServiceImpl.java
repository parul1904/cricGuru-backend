package in.cricguru.service.impl;

import in.cricguru.mapper.DreamTeamMapper;
import in.cricguru.repository.StatsRepository;
import in.cricguru.response.DreamTeamResponse;
import in.cricguru.response.PlayerPerformanceResponse;
import in.cricguru.service.DreamTeamService;
import in.cricguru.service.StatsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DreamTeamServiceImpl implements DreamTeamService {

    private final DreamTeamMapper dreamTeamMapper;
    private final StatsService statsService;

    public DreamTeamServiceImpl(DreamTeamMapper dreamTeamMapper,
                                StatsService statsService) {
        this.dreamTeamMapper = dreamTeamMapper;
        this.statsService = statsService;
    }

    @Override
    public List<DreamTeamResponse> getOldDreamTeamByMatchNo(Integer teamId1, Integer teamId2) {
        List<PlayerPerformanceResponse> performanceData = statsService.getPlayerPerformanceData(teamId1, teamId2);
        return dreamTeamMapper.mapPerformanceDataToDreamTeam(performanceData);
    }

    @Override
    public List<DreamTeamResponse> getNewDreamTeamByMatchNo(Integer teamId1, Integer teamId2) {
        List<PlayerPerformanceResponse> performanceData = statsService.getPlayerPerformanceData(teamId1, teamId2);
        return dreamTeamMapper.mapPerformanceDataToDreamTeam(performanceData);
    }

    @Override
    public List<DreamTeamResponse> getMy11CircleDreamTeamByMatchNo(Integer teamId1, Integer teamId2) {
        List<PlayerPerformanceResponse> performanceData = statsService.getPlayerPerformanceData(teamId1, teamId2);
        return dreamTeamMapper.mapPerformanceDataToDreamTeam(performanceData);
    }
}
