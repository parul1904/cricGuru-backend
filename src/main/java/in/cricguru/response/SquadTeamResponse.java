package in.cricguru.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SquadTeamResponse {
    private Map<String, Object> teamDetails;
    private List<Map<String, Object>> playerDetails;

}