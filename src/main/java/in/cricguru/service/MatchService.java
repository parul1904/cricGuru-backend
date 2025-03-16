package in.cricguru.service;

import in.cricguru.response.MatchBetweenResponse;
import in.cricguru.dto.MatchDto;
import in.cricguru.response.MatchResponse;

import java.util.List;

public interface MatchService {
    MatchDto createMatch(MatchDto matchDto);
    MatchDto getMatchById(Integer id);
    List<MatchResponse> getAllMatches();
    MatchDto updateMatch(Integer id, MatchDto matchDto);
    void deleteMatch(Integer id);

    List<MatchBetweenResponse> getMatchDetailsByMatchId(Integer seasonId);

    List<MatchResponse> getAllMatchDetailsBySeasonId(Integer seasonId);

    List<MatchResponse> getUpcomingMatches(int limit);
}