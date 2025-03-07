package in.cricguru.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatsPerPlayerResponse {
    private Integer matchId;
    private Integer seasonYear;
    private Integer matchNo;
    private String player;
    private String playerImgUrl;
    private String playerRole;
    private String playerCountry;
    private Integer matchesPlayed;
    private Integer battingInnsPlayed;
    private Integer runsScored;
    private Integer ballFaced;
    private Integer fours;
    private Integer sixes;
    private Double strikeRate;
    private Double battingAverage;
    private Integer halfCentury;
    private Integer century;
    private Integer bestScore;
    private Integer catchTaken;
    private Integer stumping;
    private Integer bowlingInnsPlayed;
    private Double overs;
    private Integer runsConceded;
    private Integer totalWickets;
    private Double bowlingAverage;
    private Double economyRate;
    private Double bowlingStrikeRate;
    private Integer threeWicketHauls;
    private Boolean isImpactPlayer;
}