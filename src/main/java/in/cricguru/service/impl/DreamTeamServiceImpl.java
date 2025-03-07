package in.cricguru.service.impl;

import in.cricguru.mapper.DreamTeamMapper;
import in.cricguru.repository.StatsRepository;
import in.cricguru.response.DreamTeamResponse;
import in.cricguru.service.DreamTeamService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DreamTeamServiceImpl implements DreamTeamService {

    private final StatsRepository statsRepository;

    private final DreamTeamMapper dreamTeamMapper;

    public DreamTeamServiceImpl(StatsRepository statsRepository, DreamTeamMapper dreamTeamMapper) {
        this.statsRepository = statsRepository;
        this.dreamTeamMapper = dreamTeamMapper;
    }


    public List<DreamTeamResponse> getDreamTeamByMatchNo(Integer matchNo) {
        List<Object[]> dbResults = statsRepository.getDreamTeamByMatchNo(Long.valueOf(matchNo)).stream()
                .collect(Collectors.toList());
        return dreamTeamMapper.mapToDreamTeamResponse(dbResults);
    }
}
