package in.cricguru.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private Integer playerId;
    private String playerName;
    private String nickName;
    private String playerImgUrl;
    private String role;
    private String battingStyle;
    private String bowlingStyle;
    private String country;
}