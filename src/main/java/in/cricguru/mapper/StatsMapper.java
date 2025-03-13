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
import in.cricguru.response.VenueStatsResponse;
import in.cricguru.util.Dream11NewPointCalculator;
import in.cricguru.util.Dream11OldPointCalculator;
import in.cricguru.util.My11CirclePointCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

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
                stats.getIsDismissed(),
                stats.getInningPlayed(),
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
        stats.setIsDismissed(statsDto.getIsDismissed());
        stats.setInningPlayed(statsDto.getInningPlayed());
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
            statsResponse.setMatchNo((Integer) row[2]);
            statsResponse.setTeam1((String) row[3]);
            statsResponse.setTeam2((String) row[4]);
            statsResponse.setMatchDate(((java.sql.Date) row[5]).toLocalDate());
            statsResponse.setPlayerName((String) row[6]);
            statsResponse.setDream11Points((Integer) row[7]);
            statsResponseList.add(statsResponse);
        }
        return statsResponseList;
    }

    public List<StatsPerMatchResponse> mapToStatsPerMatchResponseList(List<Object[]> result) {
        List<StatsPerMatchResponse> statsResponseList = new ArrayList<>();
        for (Object[] row : result) {
            StatsPerMatchResponse response = new StatsPerMatchResponse();
            response.setPlayerName(row[0] != null ? ((String) row[0]) : null);
            response.setPlayerImage(row[1] != null ? ((String) row[1]) : null);
            response.setRunsScored(row[2] != null ? ((Integer) row[2]) : 0);
            response.setFours(row[3] != null ? ((Integer) row[3]) : 0);
            response.setSixes(row[4] != null ? ((Integer) row[4]) : 0);
            response.setStrikeRate(row[5] != null ? (Double) row[5] : 0.0);
            response.setCatchTaken(row[6] != null ? ((Integer) row[6]) : 0);
            response.setOvers(row[7] != null ? ((Double) row[7]) : 0.0);
            response.setTotalWickets(row[8] != null ? ((Integer) row[8]) : 0);
            response.setRunsConceded(row[9] != null ? ((Integer) row[9]) : 0);
            response.setDots(row[10] != null ? ((Integer) row[10]) : 0);
            response.setEconomyRate(row[11] != null ? (Double) row[11] : 0.0);
            response.setTotalPointDream11OldSystem(row[12] != null ? ((Integer) row[12]) : 0);
            response.setTotalPointDream11NewSystem(row[13] != null ? ((Integer) row[13]) : 0);
            response.setTotalPointMy11CircleSystem(row[14] != null ? ((Integer) row[14]) : 0);
            statsResponseList.add(response);
        }
        return statsResponseList;
    }

    public StatsPerPlayerResponse mapToStatsPerPlayerResponseList(List<Object[]> result) {
        StatsPerPlayerResponse response = new StatsPerPlayerResponse();
        for (Object[] row : result) {
            response.setBattingInnsPlayed(row[0] != null ? ((BigDecimal) row[0]).intValue() : 0);
            response.setRunsScored(row[1] != null ? ((BigDecimal) row[1]).intValue() : 0);
            response.setBallFaced(row[2] != null ? ((BigDecimal) row[2]).intValue() : 0);
            response.setFours(row[3] != null ? ((BigDecimal) row[3]).intValue() : 0);
            response.setSixes(row[4] != null ? ((BigDecimal) row[4]).intValue() : 0);
            response.setStrikeRate(row[5] != null ? (Double) row[5] : 0.0);
            response.setHalfCentury(row[6] != null ? ((BigDecimal) row[6]).intValue() : 0);
            response.setCentury(row[7] != null ? ((BigDecimal) row[7]).intValue() : 0);
            response.setCatchTaken(row[8] != null ? ((BigDecimal) row[8]).intValue() : 0);
            response.setStumping(row[9] != null ? ((BigDecimal) row[9]).intValue() : 0);
            response.setBowlingInnsPlayed(row[10] != null ? ((BigDecimal) row[10]).intValue() : 0);
            response.setOvers(row[11] != null ? (Double) row[11] : 0.0);
            response.setTotalWickets(row[12] != null ? ((BigDecimal) row[12]).intValue() : 0);
            response.setRunsConceded(row[13] != null ? ((BigDecimal) row[13]).intValue() : 0);
            response.setDots(row[14] != null ? ((BigDecimal) row[14]).intValue() : 0);
            response.setMaidens(row[15] != null ? ((BigDecimal) row[15]).intValue() : 0);
            response.setBowlingAverage(row[16] != null ? ((BigDecimal) row[16]).doubleValue() : 0.0);
            response.setEconomyRate(row[17] != null ? (Double) row[17] : 0.0);
            response.setMatchesPlayed(row[18] != null ? ((Long) row[18]).intValue() : 0);
        }
        return response;
    }


    public StatsPerPlayerResponse mapToStatsPerTeamResponseList(List<Object[]> result) {
        StatsPerPlayerResponse response = new StatsPerPlayerResponse();
        for (Object[] row : result) {
            response.setTeamName(row[0] != null ? ((String) row[0]) : null);
            response.setRunsScored(row[1] != null ? ((BigDecimal) row[1]).intValue() : 0);
            response.setBallFaced(row[2] != null ? ((BigDecimal) row[2]).intValue() : 0);
            response.setFours(row[3] != null ? ((BigDecimal) row[3]).intValue() : 0);
            response.setSixes(row[4] != null ? ((BigDecimal) row[4]).intValue() : 0);
            response.setStrikeRate(row[5] != null ? (Double) row[5] : 0.0);
            response.setHalfCentury(row[6] != null ? ((BigDecimal) row[6]).intValue() : 0);
            response.setCentury(row[7] != null ? ((BigDecimal) row[7]).intValue() : 0);
            response.setCatchTaken(row[8] != null ? ((BigDecimal) row[8]).intValue() : 0);
            response.setStumping(row[9] != null ? ((BigDecimal) row[9]).intValue() : 0);
            response.setBowlingInnsPlayed(row[10] != null ? ((BigDecimal) row[10]).intValue() : 0);
            response.setOvers(row[11] != null ? (Double) row[11] : 0.0);
            response.setTotalWickets(row[12] != null ? ((BigDecimal) row[12]).intValue() : 0);
            response.setRunsConceded(row[13] != null ? ((BigDecimal) row[13]).intValue() : 0);
            response.setDots(row[14] != null ? ((BigDecimal) row[14]).intValue() : 0);
            response.setMaidens(row[15] != null ? ((BigDecimal) row[15]).intValue() : 0);
            response.setBowlingAverage(row[16] != null ? ((BigDecimal) row[16]).doubleValue() : 0.0);
            response.setEconomyRate(row[17] != null ? (Double) row[17] : 0.0);
        }
        return response;
    }

    public VenueStatsResponse mapToStatsPerVenue(List<Object[]> result) {
        VenueStatsResponse response = new VenueStatsResponse();
        for (Object[] row : result) {
            response.setBattingInnsPlayed(row[0] != null ? ((BigDecimal) row[0]).intValue() : 0);
            response.setRunsScored(row[1] != null ? ((BigDecimal) row[1]).intValue() : 0);
            response.setBallFaced(row[2] != null ? ((BigDecimal) row[2]).intValue() : 0);
            response.setFours(row[3] != null ? ((BigDecimal) row[3]).intValue() : 0);
            response.setSixes(row[4] != null ? ((BigDecimal) row[4]).intValue() : 0);
            response.setStrikeRate(row[5] != null ? (Double) row[5] : 0.0);
            response.setHalfCentury(row[6] != null ? ((BigDecimal) row[6]).intValue() : 0);
            response.setCentury(row[7] != null ? ((BigDecimal) row[7]).intValue() : 0);
            response.setCatchTaken(row[8] != null ? ((BigDecimal) row[8]).intValue() : 0);
            response.setStumping(row[9] != null ? ((BigDecimal) row[9]).intValue() : 0);
            response.setBowlingInnsPlayed(row[10] != null ? ((BigDecimal) row[10]).intValue() : 0);
            response.setOvers(row[11] != null ? (Double) row[11] : 0.0);
            response.setTotalWickets(row[12] != null ? ((BigDecimal) row[12]).intValue() : 0);
            response.setRunsConceded(row[13] != null ? ((BigDecimal) row[13]).intValue() : 0);
            response.setDots(row[14] != null ? ((BigDecimal) row[14]).intValue() : 0);
            response.setMaidens(row[15] != null ? ((BigDecimal) row[15]).intValue() : 0);
            response.setBowlingAverage(row[16] != null ? ((BigDecimal) row[16]).doubleValue() : 0.0);
            response.setEconomyRate(row[17] != null ? (Double) row[17] : 0.0);
            response.setMatchesPlayed(row[18] != null ? ((Long) row[18]).intValue() : 0);
        }
        return response;
    }

    /*
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
     */

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
