package in.cricguru.mapper;

import in.cricguru.dto.StatsDto;
import in.cricguru.entity.Match;
import in.cricguru.entity.Player;
import in.cricguru.entity.Season;
import in.cricguru.entity.Stats;
import in.cricguru.response.ListStatsResponse;
import in.cricguru.response.StatsPerMatchResponse;
import in.cricguru.response.StatsPerPlayerResponse;
import in.cricguru.repository.MatchRepository;
import in.cricguru.repository.PlayerRepository;
import in.cricguru.repository.SeasonRepository;
import in.cricguru.repository.TeamRepository;
import in.cricguru.util.Dream11NewPointCalculator;
import in.cricguru.util.Dream11OldPointCalculator;
import in.cricguru.util.My11CirclePointCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StatsMapper {

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    public StatsDto mapToStatsDto(Stats stats) {
        Match match = stats.getMatchNo();
        return new StatsDto(
                stats.getMatchStatsId(),
                stats.getSeason().getSeasonId(),
                stats.getMatchNo().getMatchId(),
                stats.getPlayerId().getPlayerId(),
                stats.getRunsScored(),
                stats.getBallFaced(),
                stats.getFours(),
                stats.getSixes(),
                stats.getStrikeRate(),
                stats.getOvers(),
                stats.getTotalWickets(),
                stats.getBowledLbw(),
                stats.getOtherDismissal(),
                stats.getRunsConceded(),
                stats.getDots(),
                stats.getMaiden(),
                stats.getEconomyRate(),
                stats.getCatchTaken(),
                stats.getStumping(),
                stats.getDirectRunout(),
                stats.getInDirectRunout(),
                stats.getIsImpactPlayer(),
                stats.getTotalPointDream11NewSystem(),
                stats.getTotalPointDream11OldSystem(),
                stats.getTotalPointMy11CircleSystem()
        );
    }

    public Stats mapToStats(StatsDto statsDto) {
        Stats stats = new Stats();
        stats.setSeason(seasonRepository.findById(Long.valueOf(statsDto.getSeasonId())).orElseThrow());
        stats.setMatchNo(matchRepository.findById(Math.toIntExact(Long.valueOf(statsDto.getMatchId()))).orElseThrow());
        Optional<Player> playerDetails = playerRepository.findById(Long.valueOf(statsDto.getPlayerId()));
        stats.setPlayerId(playerDetails.orElseThrow());
        stats.setRunsScored(statsDto.getRunsScored());
        stats.setBallFaced(statsDto.getBallFaced());
        stats.setFours(statsDto.getFours());
        stats.setSixes(statsDto.getSixes());
        stats.setStrikeRate(statsDto.getStrikeRate());
        stats.setOvers(statsDto.getOvers());
        stats.setTotalWickets(statsDto.getTotalWickets());
        stats.setBowledLbw(statsDto.getBowledLbw());
        stats.setOtherDismissal(statsDto.getOtherDismissal());
        stats.setRunsConceded(statsDto.getRunsConceded());
        stats.setDots(statsDto.getDots());
        stats.setMaiden(statsDto.getMaiden());
        stats.setEconomyRate(statsDto.getEconomyRate());
        stats.setCatchTaken(statsDto.getCatchTaken());
        stats.setStumping(statsDto.getStumping());
        stats.setDirectRunout(statsDto.getDirectRunout());
        stats.setInDirectRunout(statsDto.getInDirectRunout());
        stats.setIsImpactPlayer(statsDto.getIsImpactPlayer());
        int totalPointsFoDream11NewSystem = Dream11NewPointCalculator.calculatePoints(statsDto, playerDetails.get().getRole());
        stats.setTotalPointDream11NewSystem(totalPointsFoDream11NewSystem);
        int totalPointsForDream11OldSystem = Dream11OldPointCalculator.calculatePoints(statsDto, playerDetails.get().getRole());
        stats.setTotalPointDream11OldSystem(totalPointsForDream11OldSystem);
        int totalPointsForMy11CircleSystem = My11CirclePointCalculator.calculatePoints(statsDto, playerDetails.get().getRole());
        stats.setTotalPointMy11CircleSystem(totalPointsForMy11CircleSystem);
        return stats;
    }

    public List<ListStatsResponse> mapToStatsResponseList(List<Object[]> statsList) {
        List<ListStatsResponse> statsResponseList = new ArrayList<>();
        for (Object[] row : statsList) {
            ListStatsResponse statsResponse = new ListStatsResponse();
            statsResponse.setId((Integer) row[0]);
            statsResponse.setSeasonYear(String.valueOf((Integer) row[1]));
            statsResponse.setTeam1((String) row[2]);
            statsResponse.setTeam2((String) row[3]);
            statsResponse.setMatchDate(((java.sql.Date) row[4]).toLocalDate());
            statsResponse.setPlayerName((String) row[5]);
            statsResponse.setDream11Points((Integer) row[6]);
            statsResponseList.add(statsResponse);
        }
        return statsResponseList;
    }

    public List<StatsPerMatchResponse> mapToStatsPerMatchResponseList(List<Object[]> result) {
        List<StatsPerMatchResponse> statsResponseList = new ArrayList<>();
        for (Object[] row : result) {
            StatsPerMatchResponse response = new StatsPerMatchResponse();
            response.setMatchId((Integer) row[2]);
            Season season = seasonRepository.findById(Long.valueOf((Integer) row[1])).orElseThrow();
            response.setSeasonYear(String.valueOf(season.getYear()));
            Match match = matchRepository.findById(Math.toIntExact(Long.valueOf((Integer) row[2]))).orElseThrow();
            response.setTeam1(match.getTeam1().getTeamLogoUrl());
            response.setTeam2(match.getTeam2().getTeamLogoUrl());
            response.setMatchDate(match.getMatchDate().toString());
            Player player = playerRepository.findById(Long.valueOf((Integer) row[3])).orElseThrow();
            response.setPlayerImage(player.getPlayerImgUrl());
            response.setPlayerName(player.getPlayerName());
            response.setRunsScored((Integer) row[4]);
            response.setBallFaced((Integer) row[5]);
            response.setFours((Integer) row[6]);
            response.setSixes((Integer) row[7]);
            response.setStrikeRate((Double) row[8]);
            response.setOvers((Double) row[9]);
            response.setTotalWickets((Integer) row[10]);
            response.setBowledLbw((Integer) row[11]);
            response.setOtherDismissal((Integer) row[12]);
            response.setRunsConceded((Integer) row[13]);
            response.setDots((Integer) row[14]);
            response.setMaiden((Integer) row[15]);
            response.setEconomyRate((Double) row[16]);
            response.setCatchTaken((Integer) row[17]);
            response.setStumping((Integer) row[18]);
            response.setDirectRunout((Integer) row[19]);
            response.setInDirectRunout((Integer) row[20]);
            response.setIsImpactPlayer((Boolean) row[21]);
            response.setTotalPointDream11NewSystem((Integer) row[22]);
            response.setTotalPointDream11OldSystem((Integer) row[23]);
            response.setTotalPointMy11CircleSystem((Integer) row[24]);
            statsResponseList.add(response);
        }
        return statsResponseList;
    }

    public StatsPerPlayerResponse mapToStatsPerPlayerResponseList(List<Object[]> result) {
        List<StatsPerPlayerResponse> statsResponseList = new ArrayList<>();
        for (Object[] row : result) {
            StatsPerPlayerResponse response = new StatsPerPlayerResponse();
            response.setSeasonYear((Integer) row[1]);
            response.setMatchNo((Integer) row[2]);
            response.setPlayer((String) row[3]);
            response.setPlayerImgUrl((String) row[4]);
            response.setPlayerRole((String) row[5]);
            response.setPlayerCountry((String) row[6]);
            response.setRunsScored((Integer) row[7]);
            response.setBallFaced((Integer) row[8]);
            response.setFours((Integer) row[9]);
            response.setSixes((Integer) row[10]);
            response.setStrikeRate((Double) row[11]);
            response.setOvers((Double) row[12]);
            response.setTotalWickets((Integer) row[13]);
            response.setRunsConceded((Integer) row[14]);
            response.setEconomyRate((Double) row[15]);
            response.setCatchTaken((Integer) row[16]);
            response.setStumping((Integer) row[17]);
            response.setIsImpactPlayer((Boolean) row[18]);
            statsResponseList.add(response);
        }
        return aggregateStats(statsResponseList);
    }

    public StatsPerPlayerResponse aggregateStats(List<StatsPerPlayerResponse> statsList) {
        StatsPerPlayerResponse aggregatedResponse = new StatsPerPlayerResponse();
        int totalRunsScored = 0;
        int totalBallFaced = 0;
        int totalFours = 0;
        int totalSixes = 0;
        double totalStrikeRate = 0.0;
        int totalCatchTaken = 0;
        int totalStumping = 0;
        int totalWickets = 0;
        int totalOvers = 0;
        int totalRunsConceded = 0;
        double totalEconomyRate = 0.0;
        int battingInnCounter = 0;
        int bowlingInnCounter = 0;
        int halfCenturyCounter = 0;
        int centuryCounter = 0;
        int bestScore = 0;
        int bestBowlingFigureWicket = 0;
        int bestBowlingFigureRun = 0;
        int threeWicketHaulsCounter = 0;

        for (StatsPerPlayerResponse stats : statsList) {
            totalRunsScored += null!= stats.getRunsScored() ? stats.getRunsScored() : 0;
            if (null != stats.getRunsScored() && (stats.getRunsScored() > bestScore)) {
                bestScore = stats.getRunsScored();
            }
            if (null != stats.getRunsScored() && (stats.getRunsScored() >= 50 && stats.getRunsScored() < 100)) {
                halfCenturyCounter++;
            } else if (null != stats.getRunsScored() && (stats.getRunsScored() >= 100)) {
                centuryCounter++;
            }
            totalBallFaced += null!= stats.getBallFaced() ? stats.getBallFaced() : 0;
            if (null != stats.getBallFaced() && (stats.getBallFaced() > 1)) {
                battingInnCounter++;
            }
            totalFours += null!=stats.getFours() ? stats.getFours() : 0;
            totalSixes += null!= stats.getSixes() ? stats.getSixes() : 0;
            totalStrikeRate += null!=stats.getStrikeRate() ? stats.getStrikeRate() : 0;
            totalCatchTaken += null!=stats.getCatchTaken() ? stats.getCatchTaken() : 0;
            totalStumping += null!=stats.getStumping() ? stats.getStumping() : 0;
            totalWickets += stats.getTotalWickets() != null ? stats.getTotalWickets() : 0;
            totalOvers += stats.getOvers() != null ? oversToBalls(stats.getOvers()) : 0;
            if (null != stats.getOvers() && stats.getOvers() > 0.1) {
                bowlingInnCounter++;
            }
            totalRunsConceded += stats.getRunsConceded() != null ? stats.getRunsConceded() : 0;
            totalEconomyRate += stats.getEconomyRate() != null ? stats.getEconomyRate() : 0.0;

            //find best bowling figure
            //TODO

            if(null!=stats.getTotalWickets() && stats.getTotalWickets() >= 3){
                threeWicketHaulsCounter++;
            }
        }

        if(!statsList.isEmpty() && null!=statsList.get(0).getMatchesPlayed()
                && statsList.get(0).getMatchesPlayed() > 0){
            aggregatedResponse.setMatchesPlayed(statsList.get(0).getMatchesPlayed());
        } else  {
            aggregatedResponse.setMatchesPlayed(0);
        }
        aggregatedResponse.setBattingInnsPlayed(battingInnCounter);
        aggregatedResponse.setSeasonYear(!statsList.isEmpty() ? statsList.get(0).getSeasonYear() : null);
        aggregatedResponse.setPlayer(!statsList.isEmpty() ? statsList.get(0).getPlayer() : null);
        aggregatedResponse.setPlayerImgUrl(!statsList.isEmpty() ? statsList.get(0).getPlayerImgUrl() : null);
        aggregatedResponse.setPlayerRole(!statsList.isEmpty() ? statsList.get(0).getPlayerRole() : null);
        aggregatedResponse.setPlayerCountry(!statsList.isEmpty() ? statsList.get(0).getPlayerCountry() : null);
        aggregatedResponse.setRunsScored(totalRunsScored);
        aggregatedResponse.setBallFaced(totalBallFaced);
        aggregatedResponse.setFours(totalFours);
        aggregatedResponse.setSixes(totalSixes);
        if(!statsList.isEmpty()) {
            aggregatedResponse.setStrikeRate(totalStrikeRate / statsList.size());
        } else {
            aggregatedResponse.setStrikeRate(0.0);
        }

        if(battingInnCounter > 0) {
            aggregatedResponse.setBattingAverage((double) (totalRunsScored / battingInnCounter));
        } else {
            aggregatedResponse.setBattingAverage((double) totalRunsScored);
        }
        aggregatedResponse.setHalfCentury(halfCenturyCounter);
        aggregatedResponse.setCentury(centuryCounter);
        aggregatedResponse.setBestScore(bestScore);
        aggregatedResponse.setCatchTaken(totalCatchTaken);
        aggregatedResponse.setStumping(totalStumping);
        aggregatedResponse.setBowlingInnsPlayed(bowlingInnCounter);
        aggregatedResponse.setTotalWickets(totalWickets);
        aggregatedResponse.setOvers(ballsToOvers(totalOvers));
        aggregatedResponse.setRunsConceded(totalRunsConceded);
        if(totalWickets > 0){
            aggregatedResponse.setBowlingAverage((double) (totalRunsConceded / totalWickets));
        } else {
            aggregatedResponse.setBowlingAverage((double) totalRunsConceded);
        }

        if(!statsList.isEmpty()) {
            aggregatedResponse.setEconomyRate(totalEconomyRate / statsList.size());
        } else {
            aggregatedResponse.setEconomyRate(0.0);
        }
        if(totalWickets > 0) {
            aggregatedResponse.setBowlingStrikeRate(ballsToOvers(totalOvers) / totalWickets);
        } else {
            aggregatedResponse.setBowlingStrikeRate(ballsToOvers(totalOvers));
        }
        aggregatedResponse.setThreeWicketHauls(threeWicketHaulsCounter);
        return aggregatedResponse;
    }

    public static int oversToBalls(double overs) {
        if (overs < 0) {
            throw new IllegalArgumentException("Overs cannot be negative.");
        }
        int fullOvers = (int) overs; // Get the whole number of overs
        double fractionalOvers = overs - fullOvers; // Get the fractional part
        int totalBalls = (fullOvers * 6) + (int) (fractionalOvers * 6);
        return totalBalls;
    }

    public static double ballsToOvers(int balls) {
        if (balls < 0) {
            throw new IllegalArgumentException("Balls cannot be negative.");
        }
        int fullOvers = balls / 6;
        int remainingBalls = balls % 6;
        return fullOvers + (remainingBalls / 10.0); // Represent remaining balls as a decimal
    }
}