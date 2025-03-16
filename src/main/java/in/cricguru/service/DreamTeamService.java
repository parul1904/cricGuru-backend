package in.cricguru.service;

import in.cricguru.response.DreamTeamResponse;

import java.util.List;

public interface DreamTeamService {
    List<DreamTeamResponse> getOldDreamTeamByMatchNo(Integer teamId1, Integer teamId2);

    List<DreamTeamResponse> getNewDreamTeamByMatchNo(Integer teamId1, Integer teamId2);

    List<DreamTeamResponse> getMy11CircleDreamTeamByMatchNo(Integer teamId1, Integer teamId2);
}