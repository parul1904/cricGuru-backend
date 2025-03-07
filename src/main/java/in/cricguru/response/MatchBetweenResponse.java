package in.cricguru.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchBetweenResponse {
    private String team1Name;
    private String team2Name;
    private String matchDate;
}