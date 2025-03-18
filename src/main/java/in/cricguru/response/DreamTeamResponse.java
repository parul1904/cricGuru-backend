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
    private Double averageDream11Points;
    private Double averageMy11Points;
}