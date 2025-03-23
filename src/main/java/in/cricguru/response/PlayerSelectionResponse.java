package in.cricguru.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PlayerSelectionResponse {

    private Integer playerId;
    private String playerNickName;
    private String playerImgUrl;
    private String playerRole;
    private Boolean isCaptain;
    private Boolean isViceCaptain;
    private Boolean playing15;

    @JsonIgnore
    public boolean isStaff() {
        return "Staff".equalsIgnoreCase(playerRole);
    }

    @JsonIgnore
    public boolean isPlayer() {
        return !isStaff();
    }
}