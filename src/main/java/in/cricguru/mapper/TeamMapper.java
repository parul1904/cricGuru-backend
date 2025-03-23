package in.cricguru.mapper;

import in.cricguru.dto.PlayerSquadDTO;
import in.cricguru.dto.TeamDto;
import in.cricguru.entity.Team;
import in.cricguru.response.DreamTeamResponse;

import java.util.ArrayList;
import java.util.List;

public class TeamMapper {

    public static TeamDto mapToTeamDto(Team team) {
        return new TeamDto(
                team.getTeamId(),
                team.getTeamName(),
                team.getTeamShortName(),
                team.getTeamLogoUrl(),
                team.getCaptain(),
                team.getCoach(),
                team.getVenue(),
                team.getTitleWon()
        );
    }

    public static Team mapToTeam(TeamDto teamDto) {
        return new Team(
                teamDto.getTeamId(),
                teamDto.getTeamName(),
                teamDto.getTeamShortName(),
                teamDto.getTeamLogoUrl(),
                teamDto.getCaptain(),
                teamDto.getCoach(),
                teamDto.getVenue(),
                teamDto.getTitleWon()
        );
    }

    public static List<PlayerSquadDTO> mapToPlayerSquadDTO(List<Object[]> dbResults) {
        List<PlayerSquadDTO> playerSquadDTOs = new ArrayList<>();
        for (Object[] row : dbResults) {
            PlayerSquadDTO response = new PlayerSquadDTO();
            response.setPlayerId(Long.valueOf((Integer) row[0]));
            response.setPlayerName((String) row[1]);
            response.setIsPlaying11((Boolean) row[2]);
            response.setIsCaptain((Boolean) row[3]);
            response.setIsViceCaptain((Boolean) row[4]);
            response.setIsPlaying15((Boolean) row[5]);
            playerSquadDTOs.add(response);
        }
        return playerSquadDTOs;
    }
}