package in.cricguru.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRoleUpdate {
    private Long playerId;
    private Integer teamNumber;
    private Boolean isPlaying11;
    private Boolean isCaptain;
    private Boolean isViceCaptain;
    private Boolean isPlaying15;
}