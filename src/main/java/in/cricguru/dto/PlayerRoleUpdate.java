package in.cricguru.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRoleUpdate {
    private Integer matchId;
    private Integer playerId;
    private Boolean isPlaying11;
    private Boolean isPlaying15;
    private Boolean isCaptain;
    private Boolean isViceCaptain;
    private Boolean isDreamTeam;
    private Double selectionPercentage;
}
