package in.cricguru.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchResponse {
    private Integer matchId;
    private Integer seasonYear;
    private Integer matchNo;
    private String team1;
    private String team2;
    private String venueName;
    private LocalDate matchDate;
    private String matchTime;
    private String winnerTeam;
    private String winningMargin;
    private String playerOfTheMatch;
    private String mvp;
} 