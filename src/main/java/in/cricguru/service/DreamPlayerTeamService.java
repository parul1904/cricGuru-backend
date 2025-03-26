package in.cricguru.service;

import in.cricguru.dto.DreamPlayerTeamDto;
import in.cricguru.dto.PlayerRoleUpdate;

import java.util.List;

public interface DreamPlayerTeamService {
    void saveBatch(List<DreamPlayerTeamDto> dreamPlayerTeams);
    List<DreamPlayerTeamDto> getByMatch(Integer matchNo);

    void updatePlayerRoles(List<PlayerRoleUpdate> updates);
}