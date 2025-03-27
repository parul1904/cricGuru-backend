package in.cricguru.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerPerformanceResponse {
    private Integer playerId;
    private String playerName;
    private String playerImageUrl;
    private String role;
    private String battingStyle;
    private String bowlingStyle;
    private Integer dream11Points;
    private Integer my11Points;
    private Double averageDream11Points;
    private Double averageMy11Points;
    private Double averageDream11Last5MatchPoints;
    private Integer highestPoints;
    private Integer lowestPoints;
    private Integer lastMatchPoints;
    private Integer lastMatchNo;
    private List<MatchPerformance> recentMatches;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MatchPerformance {
        private Integer matchId;
        private LocalDate matchDate;
        private String team1Name;
        private String team1Logo;
        private String team2Name;
        private String team2Logo;
        private Integer points;
        private Integer runsScored;
        private Integer ballFaced;
        private Integer fours;
        private Integer sixes;
        private Integer wickets;
        private Integer catches;
        private Integer stumpings;
        private Integer runOutDirect;
        private Integer runOutInDirect;
        private Double overs;
        private Integer runsConceded;
        private Boolean isPartOfDreamTeam;
        private Integer dream11OldPoints;
        private Integer my11CirclePoints;
        private Integer dream11NewPoints;
    }
}