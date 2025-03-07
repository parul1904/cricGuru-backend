package in.cricguru.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatsDto {
    private Integer matchStatsId;
    private Integer seasonId;
    private Integer matchId;
    private Integer playerId;
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