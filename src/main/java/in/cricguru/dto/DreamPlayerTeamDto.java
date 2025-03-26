package in.cricguru.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DreamPlayerTeamDto {
    private Integer matchNo;
    private Long playerId;
    private String playerName;
    private String playerRole;
    private Long teamId;
    private String teamShortName;
    private Boolean playing11;
    private Boolean playing15;
    private Boolean isCaptain;
    private Boolean isViceCaptain;
    private Boolean dreamTeam;
    private Double selectionPercentage;

    // Add validation method
    public void validate() {
        if (matchNo == null) {
            throw new IllegalArgumentException("matchNo cannot be null");
        }
        if (playerId == null) {
            throw new IllegalArgumentException("playerId cannot be null");
        }
    }
}
