package in.cricguru.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SquadDto {
    private Integer squadId;
    private Integer seasonId;
    private Integer teamId;
    private Integer playerId;
    private Boolean playing11;
}