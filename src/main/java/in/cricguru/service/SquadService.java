package in.cricguru.service;

import in.cricguru.dto.DreamPlayerTeamDto;
import in.cricguru.dto.SquadDto;
import in.cricguru.response.SquadResponse;
import in.cricguru.response.SquadTeamResponse;

import java.util.List;

public interface SquadService {
    List<SquadResponse> getAllSquads();

    SquadDto createSquad(SquadDto squad);

    SquadDto getSquadById(Long squadId);

    SquadDto updateSquad(Long squadId, SquadDto squadDto);

    void deleteSquad(Long squadId);

    SquadTeamResponse getSquadDetailsByTeam(Long teamId);

        List<DreamPlayerTeamDto> getSquadPlayersByTeams( Long team1Id, Long team2Id);
}