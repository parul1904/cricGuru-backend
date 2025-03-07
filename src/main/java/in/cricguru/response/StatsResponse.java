package in.cricguru.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatsResponse {
    private Integer seasonYear;
    private Integer matchNo;
    private String playerName;
    private String playerImg;
    private Integer runScored;
    private Integer fours;
    private Integer sixes;
    private Double strikeRate;
    private Double overs;
    private Integer totalWickets;
    private Integer bowledLbw;
    private Integer otherDismissal;
    private Integer dots;
    private Integer maiden;
    private Double economyRate;
    private Integer catchTaken;
    private Integer stumping;
    private Integer directRunout;
    private Integer inDirectRunout;
    private Boolean isImpactPlayer;
    private Integer totalPointNewSystem;
    private Integer totalPointOldSystem;
}