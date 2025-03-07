package in.cricguru.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatsPerMatchResponse {
    private Integer matchId;
    private String seasonYear;
    private String team1;
    private String team2;
    private String matchDate;
    private String playerImage;
    private String playerName;
    private Integer runsScored;
    private Integer ballFaced;
    private Integer fours;
    private Integer sixes;
    private Double strikeRate;
    private Double overs;
    private Integer totalWickets;
    private Integer bowledLbw;
    private Integer otherDismissal;
    private Integer runsConceded;
    private Integer dots;
    private Integer maiden;
    private Double economyRate;
    private Integer catchTaken;
    private Integer stumping;
    private Integer directRunout;
    private Integer inDirectRunout;
    private Boolean isImpactPlayer;
    private Integer totalPointDream11NewSystem;
    private Integer totalPointDream11OldSystem;
    private Integer totalPointMy11CircleSystem;
}