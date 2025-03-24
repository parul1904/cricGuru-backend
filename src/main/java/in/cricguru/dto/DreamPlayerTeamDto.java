package in.cricguru.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DreamPlayerTeamDto {
    private Long dreamPlayerTeamId;
    private Integer matchNo;
    private Long playerId;
    private Boolean dreamTeam;
    private Boolean lastMatch;
    private Boolean last3Match;
    private Boolean last5Match;
    private Boolean isCaptain;
    private Boolean isViceCaptain;
    private Boolean playing15;
    private String playerName;
    private String playerRole;
    private Long teamId;
    private String teamShortName;

    // Add this constructor for squad query
    public DreamPlayerTeamDto(Long playerId, String playerName, String playerRole, Long teamId, String teamShortName) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerRole = playerRole;
        this.teamId = teamId;
        this.teamShortName = teamShortName;
        // Initialize boolean fields to false
        this.dreamTeam = false;
        this.lastMatch = false;
        this.last3Match = false;
        this.last5Match = false;
        this.isCaptain = false;
        this.isViceCaptain = false;
        this.playing15 = false;
    }
}
