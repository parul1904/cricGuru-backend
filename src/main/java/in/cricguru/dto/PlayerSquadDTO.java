package in.cricguru.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerSquadDTO {
    private Long playerId;
    private String playerName;
    private Boolean isPlaying11;
    private Boolean isCaptain;
    private Boolean isViceCaptain;
    private Boolean isPlaying15;
}