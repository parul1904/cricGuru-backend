package in.cricguru.service.impl;

import in.cricguru.exception.ResourceNotFoundException;
import in.cricguru.dto.TeamDto;
import in.cricguru.entity.Team;
import in.cricguru.mapper.TeamMapper;
import in.cricguru.repository.TeamRepository;
import in.cricguru.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;

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
}