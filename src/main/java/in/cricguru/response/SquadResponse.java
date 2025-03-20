package in.cricguru.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SquadResponse {
    private Integer squadId;
    private Integer seasonYear;
    private String teamLogo;
    private String playerName;
    private String playerImage;
    private Boolean playing11;
}