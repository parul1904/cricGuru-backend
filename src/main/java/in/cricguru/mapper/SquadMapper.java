package in.cricguru.mapper;

import in.cricguru.dto.SquadDto;
import in.cricguru.response.SquadResponse;
import in.cricguru.response.SquadTeamResponse;
import in.cricguru.entity.Player;
import in.cricguru.entity.Season;
import in.cricguru.entity.Squad;
import in.cricguru.entity.Team;
import in.cricguru.repository.PlayerRepository;
import in.cricguru.repository.SeasonRepository;
import in.cricguru.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SquadMapper {

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public SquadDto mapToSquadDto(Squad squad) {
        SquadDto squadDto = new SquadDto();
        squadDto.setSquadId(Math.toIntExact(squad.getSquadId()));
        squadDto.setSeasonId(Math.toIntExact(squad.getSeason().getSeasonId()));
        squadDto.setTeamId(Math.toIntExact(squad.getTeam().getTeamId()));
        squadDto.setPlayerId(Math.toIntExact(squad.getPlayer().getPlayerId()));
        return squadDto;
    }

    public SquadResponse mapToSquadResponse(Squad squad) {
        SquadResponse squadResponse = new SquadResponse();
        squadResponse.setSquadId(squad.getSquadId());
        if (null != squad.getSeason()) squadResponse.setSeasonYear(squad.getSeason().getYear());
        if (null != squad.getTeam()) squadResponse.setTeamLogo(squad.getTeam().getTeamLogoUrl());
        if (null != squad.getPlayer()) {
            squadResponse.setPlayerName(squad.getPlayer().getPlayerName());
            squadResponse.setPlayerImage(squad.getPlayer().getPlayerImgUrl());
        }
        return squadResponse;
    }

    public Squad mapToSquad(SquadDto squadDto) {
        Squad squad = new Squad();
        Optional<Season> season = seasonRepository.findById(Long.valueOf(squadDto.getSeasonId()));
        if (season.isPresent()) {
            squad.setSeason(season.get());
        }
        Team team = teamRepository.findById(Long.valueOf(squadDto.getTeamId().toString())).orElseThrow();
        squad.setTeam(team);
        Player player = playerRepository.findById(Long.valueOf(squadDto.getPlayerId().toString())).orElseThrow();
        squad.setPlayer(player);
        return squad;
    }

    public SquadTeamResponse mapToSquadTeamResponse(List<Object[]> squadDetails) {
        long seasonId = 2;
        Season season = seasonRepository.findById(seasonId).orElseThrow();
        Team team = teamRepository.findById(Long.valueOf((Integer) squadDetails.get(0)[2])).orElseThrow();
        Map<String, Object> teamMap = new HashMap<>();
        teamMap.put("teamName", team.getTeamName());
        teamMap.put("teamLogo", team.getTeamLogoUrl());
        teamMap.put("teamCaptain", team.getCaptain());
        teamMap.put("teamCoach", team.getCoach());
        teamMap.put("teamVenue", team.getVenue());
        teamMap.put("titleWon", team.getTitleWon());

        List<Map<String, Object>> playerDetailsList = new ArrayList<>();

        squadDetails.forEach(squadDetail -> {
            Map<String, Object> playerDetailsMap = new HashMap<>();
            Player player = playerRepository.findById(Long.valueOf((Integer) squadDetail[3])).orElseThrow();
            playerDetailsMap.put("playerId", player.getPlayerId());
            playerDetailsMap.put("playerImage", player.getPlayerImgUrl());
            playerDetailsMap.put("playerNickName", player.getNickName());
            playerDetailsMap.put("playerName", player.getPlayerName());
            playerDetailsMap.put("playerRole", player.getRole());
            playerDetailsMap.put("playerCountry", player.getCountry());
            playerDetailsList.add(playerDetailsMap);
        });

        SquadTeamResponse squadTeamResponse = new SquadTeamResponse();
        squadTeamResponse.setTeamDetails(teamMap);
        squadTeamResponse.setPlayerDetails(playerDetailsList);
        return squadTeamResponse;
    }
}