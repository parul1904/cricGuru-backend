package in.cricguru.mapper;

import in.cricguru.response.DreamTeamResponse;
import in.cricguru.response.MatchBetweenResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DreamTeamMapper {



    public List<DreamTeamResponse> mapToDreamTeamResponse(List<Object[]> result) {
        List<DreamTeamResponse> dreamTeamResponses = new ArrayList<>();
        for (Object[] row : result) {
            DreamTeamResponse response = new DreamTeamResponse();
            response.setPlayerId((Integer) row[0]);
            response.setPlayerImgUrl((String) row[1]);
            response.setPlayerNickName((String) row[2]);
            response.setPlayerRole((String) row[3]);
            response.setTeam1((String) row[4]);
            response.setTeam2((String) row[5]);
            response.setDream11OldPoints((Integer) row[6]);
            response.setMy11CirclePoints((Integer) row[7]);
            response.setDream11NewPoints((Integer) row[8]);
            dreamTeamResponses.add(response);
        }
        return dreamTeamResponses;
    }


    public List<MatchBetweenResponse> mapToMatchBetweenResponse(List<Object[]> result) {
        List<MatchBetweenResponse> matchBetweenResponses = new ArrayList<>();
        for (Object[] row : result) {
            MatchBetweenResponse response = new MatchBetweenResponse();
            response.setTeam1Name((String) row[0]);
            response.setTeam2Name((String) row[1]);
            response.setMatchDate(row[2].toString());
            matchBetweenResponses.add(response);
        }
        return matchBetweenResponses;
    }
}