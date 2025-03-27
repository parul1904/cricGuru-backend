package in.cricguru.service;

import in.cricguru.response.DreamTeamResponse;
import in.cricguru.response.PlayerSelectionResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DreamTeamService {

    List<DreamTeamResponse> getOldDreamTeamByMatchNo(Integer matchNo);

    List<DreamTeamResponse> getNewDreamTeamByMatchNo(Integer matchNo);

    List<DreamTeamResponse> getMy11CircleDreamTeamByMatchNo(Integer matchNo);

    List<PlayerSelectionResponse> getPlayerSelectionResponses(@Param("matchId") Long matchId);

    List<Integer> getMatchesWithDreamTeam();

    List<DreamTeamResponse> getActualDreamTeamByMatchNo(Integer matchNo);
}