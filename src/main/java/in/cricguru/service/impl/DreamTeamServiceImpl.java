package in.cricguru.service.impl;

import in.cricguru.mapper.DreamTeamMapper;
import in.cricguru.repository.DreamPlayerTeamRepository;
import in.cricguru.repository.StatsRepository;
import in.cricguru.response.DreamTeamResponse;
import in.cricguru.response.PlayerPerformanceResponse;
import in.cricguru.response.PlayerSelectionResponse;
import in.cricguru.service.DreamTeamService;
import in.cricguru.service.StatsService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DreamTeamServiceImpl implements DreamTeamService {

    private final DreamTeamMapper dreamTeamMapper;
    private final StatsRepository statsRepository;
    private final DreamPlayerTeamRepository dreamPlayerTeamRepository;

    public DreamTeamServiceImpl(DreamTeamMapper dreamTeamMapper,
                                StatsRepository statsRepository, DreamPlayerTeamRepository dreamPlayerTeamRepository) {
        this.dreamTeamMapper = dreamTeamMapper;
        this.statsRepository = statsRepository;
        this.dreamPlayerTeamRepository = dreamPlayerTeamRepository;
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
    public List<PlayerSelectionResponse> getPlayerSelectionResponses(@Param("matchId") Long matchId) {
        List<Object[]> dbResults = statsRepository.getPlayerSelectionResponses(Long.valueOf(matchId)).stream()
                .collect(Collectors.toList());
        return dreamTeamMapper.mapToPlayerSelectionResponse(dbResults);
    }

    @Override
    public List<DreamTeamResponse> getActualDreamTeamByMatchNo(Integer matchNo) {

        List<Object[]> dbResults = dreamPlayerTeamRepository.findActualDreamTeamByMatchNo(matchNo);
        return dreamTeamMapper.mapToActualDreamTeamResponse(dbResults);
    }

    @Override
    public List<Integer> getMatchesWithDreamTeam() {
        return dreamPlayerTeamRepository.findMatchesWithDreamTeam();
    }
}
