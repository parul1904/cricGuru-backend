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
public class TeamDto {
    private Integer teamId;
    private String teamName;
    private String teamShortName;
    private String teamLogoUrl;
    private String captain;
    private String coach;
    private String venue;
    private String titleWon;
}