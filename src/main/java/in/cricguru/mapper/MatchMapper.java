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

    public List<MatchResponse> mapToMatchResponse(List<MatchDto> matchDtos) {
        List<MatchResponse> matchResponses = new ArrayList<>();
        for (MatchDto match : matchDtos) {
            MatchResponse response = new MatchResponse();
            Season season = seasonRepository.findById(Long.valueOf(match.getSeasonId())).orElseThrow();
            response.setMatchId(match.getMatchId());
            response.setSeasonYear(season.getYear());
            response.setMatchNo(match.getMatchNo());
            Team team1 = teamRepository.findById(Long.valueOf(match.getTeam1Id())).orElseThrow();
            response.setTeam1(team1.getTeamLogoUrl());
            Team team2 = teamRepository.findById(Long.valueOf(match.getTeam2Id())).orElseThrow();
            response.setTeam2(team2.getTeamLogoUrl());
            Venue venue = venueRepository.findById(Long.valueOf(match.getVenueId())).orElseThrow();
            response.setVenueName(venue.getVenueName());
            match.setMatchDate(match.getMatchDate());
            match.setMatchTime(match.getMatchTime());
            match.setTossWonBy(match.getTossWonBy());
            match.setTossDecision(match.getTossDecision());
            match.setFirstInnRuns(match.getFirstInnRuns());
            match.setFirstInnWickets(match.getFirstInnWickets());
            match.setSecondInnRuns(match.getSecondInnRuns());
            match.setSecondInnWickets(match.getSecondInnWickets());
            match.setWicketTakenByPacer(match.getWicketTakenByPacer());
            match.setWicketTakenBySpinner(match.getWicketTakenBySpinner());
            if (null != match.getWinnerTeamId()) {
                Team winnerTeam = teamRepository.findById(Long.valueOf(match.getWinnerTeamId())).orElseThrow();
                response.setWinnerTeam(winnerTeam.getTeamLogoUrl());
            }
            response.setWinningMargin(match.getWinningMargin());
            if (null != match.getPlayerOfTheMatch()) {
                Player playerOfTheMatch = playerRepository.findById(Long.valueOf(match.getPlayerOfTheMatch())).orElseThrow();
                response.setPlayerOfTheMatch(playerOfTheMatch.getNickName());
            }
            if (null != match.getMvp()) {
                Player mvp = playerRepository.findById(Long.valueOf(match.getMvp())).orElseThrow();
                response.setMvp(mvp.getNickName());
            }
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