package in.cricguru.service;

import in.cricguru.response.DreamTeamResponse;
import in.cricguru.response.PlayerSelectionResponse;

import java.util.List;

public interface DreamTeamService {

    List<DreamTeamResponse> getOldDreamTeamByMatchNo(Integer matchNo);

    List<DreamTeamResponse> getNewDreamTeamByMatchNo(Integer matchNo);

    List<DreamTeamResponse> getMy11CircleDreamTeamByMatchNo(Integer matchNo);

    List<PlayerSelectionResponse> getPlayerSelectionResponses(Integer team1Id, Integer team2Id);

    List<Integer> getMatchesWithDreamTeam();

    List<DreamTeamResponse> getActualDreamTeamByMatchNo(Integer matchNo);
}