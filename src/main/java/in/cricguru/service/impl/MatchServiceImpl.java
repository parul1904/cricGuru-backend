package in.cricguru.service.impl;

import in.cricguru.response.MatchBetweenResponse;
import in.cricguru.dto.MatchDto;
import in.cricguru.response.MatchResponse;
import in.cricguru.entity.Match;
import in.cricguru.mapper.MatchMapper;
import in.cricguru.repository.MatchRepository;
import in.cricguru.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    @Override
    public MatchDto createMatch(MatchDto matchDto) {
        Match match = matchMapper.mapToMatch(matchDto);
        Match savedMatch = matchRepository.save(match);
        return matchMapper.mapToMatchDto(savedMatch);
    }

    @Override
    public MatchDto getMatchById(Integer id) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found with id: " + id));
        return matchMapper.mapToMatchDto(match);
    }

    @Override
    public List<MatchResponse> getAllMatches() {
        List<Object[]> result = matchRepository.getAllMatchDetails().stream()
                .collect(Collectors.toList());
        List<MatchResponse> matchDto = matchMapper.mapToMatchResponse(result);
        return matchDto;
    }

    @Override
    public MatchDto updateMatch(Integer id, MatchDto matchDto) {
        if (!matchRepository.existsById(id)) {
            throw new RuntimeException("Match not found with id: " + id);
        }
        Match existingMatch = matchRepository.findById(id).orElseThrow();
        Match updatedMatch = matchMapper.mapToMatch(matchDto);
        updatedMatch.setMatchId(existingMatch.getMatchId());
        Match savedMatch = matchRepository.save(updatedMatch);
        return matchMapper.mapToMatchDto(savedMatch);
    }

    @Override
    public void deleteMatch(Integer id) {
        if (!matchRepository.existsById(id)) {
            throw new RuntimeException("Match not found with id: " + id);
        }
        matchRepository.deleteById(id);
    }

    @Override
    public List<MatchBetweenResponse> getMatchDetailsByMatchId(Integer seasonId) {
        List<Object[]> result = matchRepository.getMatchDetailsByMatchId(Long.valueOf(seasonId)).stream()
                .collect(Collectors.toList());
        List<MatchBetweenResponse> matchDto = matchMapper.mapToMatchBetweenResponse(result);
        return matchDto;
    }

}