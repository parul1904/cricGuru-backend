package in.cricguru.service.impl;

import in.cricguru.controller.LoginController;
import in.cricguru.dto.PlayerRoleUpdate;
import in.cricguru.dto.PlayerSquadDTO;
import in.cricguru.entity.Player;
import in.cricguru.exception.ResourceNotFoundException;
import in.cricguru.dto.TeamDto;
import in.cricguru.entity.Team;
import in.cricguru.mapper.TeamMapper;
import in.cricguru.repository.PlayerRepository;
import in.cricguru.repository.SquadRepository;
import in.cricguru.repository.TeamRepository;
import in.cricguru.service.TeamService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import in.cricguru.entity.Squad;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(TeamServiceImpl.class);

    private TeamRepository teamRepository;
    private SquadRepository squadRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Cacheable(cacheNames="allTeams")
    @Override
    public List<TeamDto> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        List<TeamDto> teamDtos = teams.stream()
                .map((team) -> TeamMapper.mapToTeamDto(team))
                .collect(Collectors.toList());
        return teamDtos;
    }

    @Override
    public TeamDto createTeam(TeamDto teamDto) {
        Team team = TeamMapper.mapToTeam(teamDto);
        Team savedTeam = teamRepository.save(team);
        return TeamMapper.mapToTeamDto(savedTeam);
    }

    @Override
    public TeamDto getTeamById(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Team does not exist with id: " + teamId));
        TeamDto teamDto = TeamMapper.mapToTeamDto(team);
        return teamDto;
    }

    @Override
    public TeamDto updateTeam(Long teamId, TeamDto teamDto) {
        Team existingTeam = teamRepository.findById(teamId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Team does not exist with id: " + teamId));
        existingTeam.setTeamName(teamDto.getTeamName());
        existingTeam.setTeamShortName(teamDto.getTeamShortName());
        existingTeam.setTeamLogoUrl(teamDto.getTeamLogoUrl());
        existingTeam.setCaptain(teamDto.getCaptain());
        existingTeam.setCoach(teamDto.getCoach());
        existingTeam.setVenue(teamDto.getVenue());
        existingTeam.setTitleWon(teamDto.getTitleWon());
        teamRepository.save(existingTeam);
        return TeamMapper.mapToTeamDto(existingTeam);
    }

    @Override
    public void deleteTeam(Long teamId) {
        Team existingTeam = teamRepository.findById(teamId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Team does not exist with id: " + teamId));
        teamRepository.deleteById(teamId);
    }

    public List<PlayerSquadDTO> getTeamPlayers(Long teamId) {
        List<Object[]> dbResults = squadRepository.findSquadDetailsByTeam(Math.toIntExact(teamId));
        return TeamMapper.mapToPlayerSquadDTO(dbResults);
    }

    @Transactional
    public void updatePlayerRoles(List<PlayerRoleUpdate> updates) {
        if (updates == null || updates.isEmpty()) {
            return;
        }
        
        try {
            for (PlayerRoleUpdate update : updates) {
                // Only pass the fields that were actually changed
                squadRepository.updatePlayerRole(
                        Long.valueOf(update.getPlayerId()),
                    2L, // seasonId
                    update.getIsPlaying11(),  // Will be null if not changed
                    update.getIsCaptain(),    // Will be null if not changed
                    update.getIsViceCaptain(), // Will be null if not changed
                    update.getIsPlaying15()    // Will be null if not changed
                );
            }
        } catch (Exception e) {
            logger.error("Failed to update player roles", e);
            throw new RuntimeException("Failed to update player roles: " + e.getMessage(), e);
        }
    }
}