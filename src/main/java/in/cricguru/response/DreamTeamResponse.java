package in.cricguru.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DreamTeamResponse {
    private Integer playerId;
    private String playerImgUrl;
    private String playerNickName;
    private String playerRole;
    private String team1;
    private String team2;
    private Integer dream11OldPoints;
    private Integer my11CirclePoints;
    private Integer dream11NewPoints;
    private Double dream11AvgVenuePoints;
    private Double my11CircleAvgVenuePoints;
    private Double dream11Last3MatchPoints;
    private Double my11CircleLast3MatchPoints;
}