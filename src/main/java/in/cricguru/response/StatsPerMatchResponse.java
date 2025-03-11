package in.cricguru.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatsPerMatchResponse {
    private String playerName;
    private String playerImage;
    private Integer runsScored;
    private Integer fours;
    private Integer sixes;
    private Double strikeRate;
    private Integer catchTaken;
    private Double overs;
    private Integer totalWickets;
    private Integer runsConceded;
    private Integer dots;
    private Double economyRate;
    private Integer totalPointDream11NewSystem;
    private Integer totalPointDream11OldSystem;
    private Integer totalPointMy11CircleSystem;
}