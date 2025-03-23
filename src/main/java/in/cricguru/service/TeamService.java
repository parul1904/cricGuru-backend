package in.cricguru.service;

import in.cricguru.dto.PlayerRoleUpdate;
import in.cricguru.dto.PlayerSquadDTO;
import in.cricguru.dto.TeamDto;
import in.cricguru.entity.Player;

import java.util.List;

public interface TeamService {
    List<TeamDto> getAllTeams();

    TeamDto createTeam(TeamDto team);

    TeamDto getTeamById(Long teamId);

    TeamDto updateTeam(Long teamId, TeamDto teamDto);

    void deleteTeam(Long teamId);

    List<PlayerSquadDTO> getTeamPlayers(Long teamId);

    void updatePlayerRoles(List<PlayerRoleUpdate> updates);
}