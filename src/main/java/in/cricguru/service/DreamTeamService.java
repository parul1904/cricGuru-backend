package in.cricguru.service;

import in.cricguru.response.DreamTeamResponse;

import java.util.List;

public interface DreamTeamService {
    List<DreamTeamResponse> getDreamTeamByMatchNo(Integer matchNo);
}