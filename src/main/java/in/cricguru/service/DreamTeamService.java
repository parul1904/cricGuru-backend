package in.cricguru.service;

import in.cricguru.response.DreamTeamResponse;

import java.util.List;

public interface DreamTeamService {
    List<DreamTeamResponse> getOldDreamTeamByMatchNo(Integer matchNo);

    List<DreamTeamResponse> getNewDreamTeamByMatchNo(Integer matchNo);

    List<DreamTeamResponse> getMy11CircleDreamTeamByMatchNo(Integer matchNo);
}