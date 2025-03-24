package in.cricguru.service.impl;

import in.cricguru.dto.SquadDto;
import in.cricguru.response.SquadResponse;
import in.cricguru.response.SquadTeamResponse;
import in.cricguru.dto.DreamPlayerTeamDto;
import in.cricguru.entity.Squad;
import in.cricguru.exception.ResourceNotFoundException;
import in.cricguru.mapper.SquadMapper;
import in.cricguru.repository.SquadRepository;
import in.cricguru.service.SquadService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SquadServiceImpl implements SquadService {

    @Autowired
    private SquadRepository squadRepository;

    @Autowired
    private SquadMapper squadMapper;

    @Cacheable(cacheNames="allSquads")
    @Override
    public List<SquadResponse> getAllSquads() {
        List<Squad> squads = squadRepository.findAll();
        List<SquadResponse> squadDtos = squads.stream()
                .map((squad) -> squadMapper.mapToSquadResponse(squad))
                .collect(Collectors.toList());
        return squadDtos;
    }

    @Override
    public SquadDto createSquad(SquadDto squadDto) {
        Squad squad = squadMapper.mapToSquad(squadDto);
        Squad savedSquad = squadRepository.save(squad);
        return squadMapper.mapToSquadDto(savedSquad);
    }

    @Cacheable(cacheNames="allSquads", key = "#squadId")
    @Override
    public SquadDto getSquadById(Long squadId) {
        Squad squad = squadRepository.findById(squadId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Squad does not exist with id: " + squadId));
        SquadDto squadDto = squadMapper.mapToSquadDto(squad);
        return squadDto;
    }

    @Override
    public SquadDto updateSquad(Long squadId, SquadDto squadDto) {
        if (!squadRepository.existsById(squadId)) {
            throw new RuntimeException("Squad not found with id: " + squadId);
        }
        Squad existingSquad = squadRepository.findById(squadId)
                .orElseThrow();
        Squad updatedSquad = squadMapper.mapToSquad(squadDto);
        updatedSquad.setSquadId(existingSquad.getSquadId());
        Squad savedSquad = squadRepository.save(updatedSquad);
        return squadMapper.mapToSquadDto(savedSquad);
    }

    @Override
    public void deleteSquad(Long squadId) {
        Squad existingSquad = squadRepository.findById(squadId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Squad does not exist with id: " + squadId));
        squadRepository.deleteById(squadId);
    }

    @Cacheable(cacheNames="squadDetailsByTeam", key = "#teamId")
    @Override
    public SquadTeamResponse getSquadDetailsByTeam(Long teamId) {
        List<Object[]> squadDetails = squadRepository.findSquadDetailsByTeam(Math.toIntExact(teamId));
       return squadMapper.mapToSquadTeamResponse(squadDetails);
    }

    @Override
    public List<DreamPlayerTeamDto> getSquadPlayersByTeams(  Long team1Id, Long team2Id) {
        List<Object[]> results = squadRepository.findSquadPlayersByTeams( team1Id, team2Id);
        return results.stream()
            .map(result -> new DreamPlayerTeamDto(
                ((Number) result[0]).longValue(),  // player_id
                (String) result[1],                // player_name
                (String) result[2],                // role
                ((Number) result[3]).longValue(),  // team_id
                (String) result[4]                 // team_short_name
            ))
            .collect(Collectors.toList());
    }


}