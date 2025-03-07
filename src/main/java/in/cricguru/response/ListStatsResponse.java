package in.cricguru.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListStatsResponse {
    private Integer id;
    private String seasonYear;
    private String team1;
    private String team2;
    private LocalDate matchDate;
    private String playerName;
    private Integer dream11Points;
}