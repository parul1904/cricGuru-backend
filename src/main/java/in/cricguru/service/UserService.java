package in.cricguru.service;

import in.cricguru.dto.MatchDto;
import in.cricguru.entity.User;
import in.cricguru.response.MatchBetweenResponse;
import in.cricguru.response.MatchResponse;

import java.util.List;

public interface UserService {
    public User findByEmail(String email);

} 