package in.cricguru.mapper;

import in.cricguru.dto.TeamDto;
import in.cricguru.entity.Team;

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
}