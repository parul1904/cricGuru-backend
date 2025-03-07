package in.cricguru.mapper;

import in.cricguru.entity.*;
import in.cricguru.response.MatchBetweenResponse;
import in.cricguru.dto.MatchDto;
import in.cricguru.response.MatchResponse;
import in.cricguru.repository.PlayerRepository;
import in.cricguru.repository.SeasonRepository;
import in.cricguru.repository.TeamRepository;
import in.cricguru.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MatchMapper {

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public MatchDto mapToMatchDto(Match match) {
        return new MatchDto(
                match.getMatchId(),
                match.getSeason().getSeasonId(),
                match.getMatchNo(),
                match.getTeam1().getTeamId(),
                match.getTeam2().getTeamId(),
                match.getVenue().getVenueId(),
                match.getMatchDate(),
                match.getMatchTime(),
                match.getTossWonBy(),
                match.getTossDecision(),
                match.getFirstInnRuns(),
                match.getFirstInnWickets(),
                match.getSecondInnRuns(),
                match.getSecondInnWickets(),
                match.getWicketTakenByPacer(),
                match.getWicketTakenBySpinner(),
                null != match.getWinnerTeam() ? match.getWinnerTeam().getTeamId() : null,
                match.getWinningMargin(),
                null != match.getPlayerOfTheMatch() ? match.getPlayerOfTheMatch().getPlayerId() : null,
                null != match.getMvpId() ? match.getMvpId().getPlayerId() : null
        );
    }

    public Match mapToMatch(MatchDto matchDto) {
        Match match = new Match();
        Season season = seasonRepository.findById(Long.valueOf(matchDto.getSeasonId())).orElseThrow();
        match.setSeason(season);
        match.setMatchNo(matchDto.getMatchNo());
        Team team1 = teamRepository.findById(Long.valueOf(matchDto.getTeam1Id())).orElseThrow();
        match.setTeam1(team1);
        Team team2 = teamRepository.findById(Long.valueOf(matchDto.getTeam2Id())).orElseThrow();
        match.setTeam2(team2);
        Venue venue = venueRepository.findById(Long.valueOf(matchDto.getVenueId())).orElseThrow();
        match.setVenue(venue);
        match.setMatchDate(matchDto.getMatchDate());
        match.setMatchTime(matchDto.getMatchTime());
        match.setTossWonBy(matchDto.getTossWonBy());
        match.setTossDecision(matchDto.getTossDecision());
        match.setFirstInnRuns(matchDto.getFirstInnRuns());
        match.setFirstInnWickets(matchDto.getFirstInnWickets());
        match.setSecondInnRuns(matchDto.getSecondInnRuns());
        match.setSecondInnWickets(matchDto.getSecondInnWickets());
        match.setWicketTakenByPacer(matchDto.getWicketTakenByPacer());
        match.setWicketTakenBySpinner(matchDto.getWicketTakenBySpinner());
        if (null != matchDto.getWinnerTeamId()) {
            Team winnerTeam = teamRepository.findById(Long.valueOf(matchDto.getWinnerTeamId())).orElseThrow();
            match.setWinnerTeam(winnerTeam);
        }
        match.setWinningMargin(matchDto.getWinningMargin());
        if (null != matchDto.getPlayerOfTheMatch()) {
            Player playerOfTheMatch = playerRepository.findById(Long.valueOf(matchDto.getPlayerOfTheMatch())).orElseThrow();
            match.setPlayerOfTheMatch(playerOfTheMatch);
        }
        if (null != matchDto.getMvp()) {
            Player mvp = playerRepository.findById(Long.valueOf(matchDto.getMvp())).orElseThrow();
            match.setMvpId(mvp);
        }
        return match;
    }

    public List<MatchResponse> mapToMatchResponse(List<Object[]> matchDtos) {
        List<MatchResponse> matchResponses = new ArrayList<>();
        List<MatchBetweenResponse> matchBetweenResponses = new ArrayList<>();

        for (Object[] row : matchDtos) {
            MatchResponse response = new MatchResponse();
            response.setMatchId((Integer) row[0]);
            response.setSeasonYear((Integer) row[1]);
            response.setMatchNo((Integer) row[2]);
            response.setTeam1((String) row[3]);
            response.setTeam2((String) row[4]);
            response.setVenueName((String) row[5]);
            response.setMatchDate(((java.sql.Date) row[6]).toLocalDate());
            response.setMatchTime((String) row[7]);
            response.setWinnerTeam((String) row[8]);
            response.setWinningMargin((String) row[9]);
            response.setPlayerOfTheMatch((String) row[10]);
            response.setMvp((String) row[11]);
            matchResponses.add(response);
        }
        return matchResponses;
    }


public List<MatchBetweenResponse> mapToMatchBetweenResponse(List<Object[]> result) {
    List<MatchBetweenResponse> matchBetweenResponses = new ArrayList<>();
    for (Object[] row : result) {
        MatchBetweenResponse response = new MatchBetweenResponse();
        response.setTeam1Name((String) row[0]);
        response.setTeam2Name((String) row[1]);
        response.setMatchDate(row[2].toString());
        matchBetweenResponses.add(response);
    }
    return matchBetweenResponses;
}
}