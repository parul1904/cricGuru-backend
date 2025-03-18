package in.cricguru.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import in.cricguru.dto.StatsDto;
import in.cricguru.entity.Match;
import in.cricguru.entity.Player;
import in.cricguru.entity.Season;
import in.cricguru.entity.Stats;
import in.cricguru.response.*;
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
import java.math.BigDecimal;
import java.util.stream.Collectors;

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
        stats.setMatchNo(matchRepository.findById(Long.valueOf(statsDto.getMatchId())).orElseThrow());
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
            // response.setPlayerId(row[0] != null ? ((Integer) row[0]) : null);
            response.setPlayerName(row[1] != null ? ((String) row[1]) : null);
            response.setPlayerImage(row[2] != null ? ((String) row[2]) : null);
            //  response.setPlayerRole(row[3] != null ? ((String) row[3]) : null);
            response.setRunsScored(row[4] != null ? ((Integer) row[4]) : 0);
            //   response.setBallFaced(row[5] != null ? ((Integer) row[5]) : 0);
            response.setFours(row[6] != null ? ((Integer) row[6]) : 0);
            response.setSixes(row[7] != null ? ((Integer) row[7]) : 0);
            response.setStrikeRate(row[8] != null ? (Double) row[8] : 0.0);
            response.setCatchTaken(row[9] != null ? ((Integer) row[9]) : 0);
            response.setOvers(row[10] != null ? ((Double) row[10]) : 0.0);
            response.setTotalWickets(row[11] != null ? ((Integer) row[11]) : 0);
            response.setRunsConceded(row[12] != null ? ((Integer) row[12]) : 0);
            response.setDots(row[13] != null ? ((Integer) row[13]) : 0);
            response.setEconomyRate(row[14] != null ? (Double) row[14] : 0.0);
            response.setTotalPointDream11OldSystem(row[15] != null ? ((Integer) row[15]) : 0);
            response.setTotalPointDream11NewSystem(row[16] != null ? ((Integer) row[16]) : 0);
            response.setTotalPointMy11CircleSystem(row[17] != null ? ((Integer) row[17]) : 0);
            // response.setMatchDate(row[18] != null ? ((java.sql.Date) row[18]).toLocalDate() : null);
            // response.setTeam1(row[19] != null ? ((String) row[19]) : null);
            // response.setTeam2(row[20] != null ? ((String) row[20]) : null);
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

    public List<PlayerPerformanceResponse> mapToPlayerPerformanceResponse(List<Object[]> results, Integer seasonId) {
        List<PlayerPerformanceResponse> responses = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        for (Object[] row : results) {
            try {
                PlayerPerformanceResponse response = new PlayerPerformanceResponse();
                response.setPlayerId((Integer) row[0]);
                response.setPlayerName((String) row[1]);
                response.setPlayerImageUrl((String) row[2]);
                response.setRole((String) row[3]);
                response.setBattingStyle((String) row[4]);
                response.setBowlingStyle((String) row[5]);
                response.setAveragePoints(row[6] != null ? ((Number) row[6]).doubleValue() : null);
                response.setHighestPoints(row[7] != null ? ((Number) row[7]).intValue() : null);
                response.setLowestPoints(row[8] != null ? ((Number) row[8]).intValue() : null);
                response.setLastMatchPoints(row[9] != null ? ((Number) row[9]).intValue() : null);
                response.setLastMatchNo(row[10] != null ? ((Number) row[10]).intValue() : null);
                if(null!=seasonId && seasonId==2){
                    response.setLast3MatchAveragePoints(row[11] != null ? ((Number) row[11]).doubleValue() : null);
                    String matchDetailsJson = (String) row[12];
                    if (matchDetailsJson != null) {
                        List<PlayerPerformanceResponse.MatchPerformance> matchPerformances = objectMapper.readValue(
                                matchDetailsJson,
                                new TypeReference<List<PlayerPerformanceResponse.MatchPerformance>>() {}
                        );
                        response.setRecentMatches(matchPerformances);
                    } else {
                        response.setRecentMatches(new ArrayList<>());
                    }
                } else {
                    String matchDetailsJson = (String) row[11];
                    if (matchDetailsJson != null) {
                        List<PlayerPerformanceResponse.MatchPerformance> matchPerformances = objectMapper.readValue(
                                matchDetailsJson,
                                new TypeReference<List<PlayerPerformanceResponse.MatchPerformance>>() {}
                        );
                        response.setRecentMatches(matchPerformances);
                    } else {
                        response.setRecentMatches(new ArrayList<>());
                    }
                }

                responses.add(response);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error parsing match details JSON", e);
            }
        }
        return responses;
    }

    public List<PlayerPerformanceResponse> mapToPlayerPerformanceResponseForMatch(List<Object[]> results, Integer matchNo) {
        List<PlayerPerformanceResponse> responses = new ArrayList<>();

        for (Object[] row : results) {
            PlayerPerformanceResponse response = new PlayerPerformanceResponse();
            response.setPlayerId((Integer) row[0]);
            response.setPlayerName((String) row[1]);
            response.setPlayerImageUrl((String) row[2]);
            response.setRole((String) row[3]);
            response.setBattingStyle((String) row[4]);
            response.setBowlingStyle((String) row[5]);

            // Create match performance
            PlayerPerformanceResponse.MatchPerformance matchPerformance = new PlayerPerformanceResponse.MatchPerformance();
            matchPerformance.setMatchNo(matchNo);
            matchPerformance.setMatchDate(((java.sql.Date) row[6]).toLocalDate());
            matchPerformance.setTeam1Name((String) row[7]);
            matchPerformance.setTeam1Logo((String) row[8]);
            matchPerformance.setTeam2Name((String) row[9]);
            matchPerformance.setTeam2Logo((String) row[10]);
            matchPerformance.setPoints(row[11] != null ? ((Number) row[11]).intValue() : 0);
            matchPerformance.setRunsScored(row[12] != null ? ((Number) row[12]).intValue() : 0);
            matchPerformance.setBallFaced(row[13] != null ? ((Number) row[13]).intValue() : 0);
            matchPerformance.setFours(row[14] != null ? ((Number) row[14]).intValue() : 0);
            matchPerformance.setSixes(row[15] != null ? ((Number) row[15]).intValue() : 0);
            matchPerformance.setWickets(row[16] != null ? ((Number) row[16]).intValue() : 0);
            matchPerformance.setOvers(row[17] != null ? ((Number) row[17]).doubleValue() : 0.0);
            matchPerformance.setRunsConceded(row[18] != null ? ((Number) row[18]).intValue() : 0);
            matchPerformance.setIsPartOfDreamTeam(row[19] != null ? (Boolean) row[19] : false);

            List<PlayerPerformanceResponse.MatchPerformance> matchPerformances = new ArrayList<>();
            matchPerformances.add(matchPerformance);
            response.setRecentMatches(matchPerformances);

            // Calculate averages and set additional fields
            response.setAveragePoints((double) matchPerformance.getPoints());
            response.setHighestPoints(matchPerformance.getPoints());
            response.setLowestPoints(matchPerformance.getPoints());
            response.setLastMatchPoints(matchPerformance.getPoints());
            response.setLastMatchNo(matchNo);

            responses.add(response);
        }
        return responses;
    }

    public List<DreamTeamResponse> mapToOldPointDreamTeamResponse(List<Object[]> performanceData) {
        List<DreamTeamResponse> allPlayers = new ArrayList<>();

        // First, create DreamTeamResponse objects for all players
        dreamTeamForSeason2(performanceData, allPlayers);

        // Sort players by points (using Dream11NewPoints) in descending order
        allPlayers.sort((p1, p2) -> {
            Integer points1 = p1.getDream11OldPoints() != null ? p1.getDream11OldPoints() : 0;
            Integer points2 = p2.getDream11OldPoints() != null ? p2.getDream11OldPoints() : 0;
            return points2.compareTo(points1);
        });

        // Return only the top 11 players
        return allPlayers.stream()
                .limit(11)
                .collect(Collectors.toList());
    }

    public List<DreamTeamResponse> mapToNewPointDreamTeamResponse(List<Object[]> performanceData) {
        List<DreamTeamResponse> allPlayers = new ArrayList<>();

        // First, create DreamTeamResponse objects for all players
        dreamTeamForSeason2(performanceData, allPlayers);

        // Sort players by points (using Dream11NewPoints) in descending order
        allPlayers.sort((p1, p2) -> {
            Integer points1 = p1.getDream11NewPoints() != null ? p1.getDream11NewPoints() : 0;
            Integer points2 = p2.getDream11NewPoints() != null ? p2.getDream11NewPoints() : 0;
            return points2.compareTo(points1);
        });

        // Return only the top 11 players
        return allPlayers.stream()
                .limit(11)
                .collect(Collectors.toList());
    }

    public List<DreamTeamResponse> mapToMy11CirclePointDreamTeamResponse(List<Object[]> performanceData) {
        List<DreamTeamResponse> allPlayers = new ArrayList<>();

        // First, create DreamTeamResponse objects for all players
        dreamTeamForSeason2(performanceData, allPlayers);

        // Sort players by points (using Dream11NewPoints) in descending order
        allPlayers.sort((p1, p2) -> {
            Integer points1 = p1.getMy11CirclePoints() != null ? p1.getMy11CirclePoints() : 0;
            Integer points2 = p2.getMy11CirclePoints() != null ? p2.getMy11CirclePoints() : 0;
            return points2.compareTo(points1);
        });

        // Return only the top 11 players
        return allPlayers.stream()
                .limit(11)
                .collect(Collectors.toList());
    }

    private static void dreamTeamForSeason2(List<Object[]> performanceData, List<DreamTeamResponse> allPlayers) {
        for (Object[] row : performanceData) {
            DreamTeamResponse response = new DreamTeamResponse();

            response.setPlayerId(row[0] != null ? ((Number) row[0]).intValue() : null);
            response.setPlayerNickName(row[1] != null ? (String) row[1] : null);
            response.setPlayerImgUrl(row[2] != null ? (String) row[2] : null);
            response.setPlayerRole(row[3] != null ? (String) row[3] : null);

            if (row[12] != null) {
                String matchDetailsJson = (String) row[12];
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                try {
                    List<PlayerPerformanceResponse.MatchPerformance> matchPerformances = objectMapper.readValue(
                            matchDetailsJson,
                            new TypeReference<List<PlayerPerformanceResponse.MatchPerformance>>() {}
                    );
                    if (!matchPerformances.isEmpty()) {
                        PlayerPerformanceResponse.MatchPerformance lastMatch = matchPerformances.get(0);
                        Integer lastMatchPoints = lastMatch.getPoints();
                        response.setDream11OldPoints(lastMatchPoints);
                        response.setMy11CirclePoints(lastMatchPoints);
                        response.setDream11NewPoints(lastMatchPoints);
                        // Set team logos from the last match
                        response.setTeam1(lastMatch.getTeam1Logo());
                        response.setTeam2(lastMatch.getTeam2Logo());
                    }
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("Error parsing match details JSON", e);
                }
            } else {
                response.setDream11OldPoints(null);
                response.setMy11CirclePoints(null);
                response.setDream11NewPoints(null);
            }

            allPlayers.add(response);
        }
    }
}