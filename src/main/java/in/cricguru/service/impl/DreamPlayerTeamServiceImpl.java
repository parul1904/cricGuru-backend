package in.cricguru.service.impl;

import in.cricguru.dto.DreamPlayerTeamDto;
import in.cricguru.dto.PlayerRoleUpdate;
import in.cricguru.entity.DreamPlayerTeam;
import in.cricguru.mapper.DreamPlayerTeamMapper;
import in.cricguru.repository.DreamPlayerTeamRepository;
import in.cricguru.service.DreamPlayerTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import in.cricguru.repository.MatchRepository;
import in.cricguru.repository.PlayerRepository;
import in.cricguru.entity.Match;
import in.cricguru.entity.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DreamPlayerTeamServiceImpl implements DreamPlayerTeamService {
    
    private final DreamPlayerTeamRepository dreamPlayerTeamRepository;
    private final DreamPlayerTeamMapper dreamPlayerTeamMapper;
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;
    private final Logger logger = LoggerFactory.getLogger(DreamPlayerTeamServiceImpl.class);

    @Override
    public void updatePlayerRoles(List<PlayerRoleUpdate> updates) {
        if (updates == null || updates.isEmpty()) {
            return;
        }
        
        try {
            for (PlayerRoleUpdate update : updates) {
                dreamPlayerTeamRepository.upsertPlayerRoles(
                    update.getMatchId(),
                    update.getPlayerId(),
                    update.getIsPlaying11(),
                    update.getIsPlaying15(),
                    update.getIsCaptain(),
                    update.getIsViceCaptain(),
                    update.getIsDreamTeam(),
                    update.getSelectionPercentage()
                );
            }
        } catch (Exception e) {
            logger.error("Failed to update player roles", e);
            throw new RuntimeException("Failed to update player roles: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void saveBatch(List<DreamPlayerTeamDto> dreamPlayerTeamDtos) {
        if (dreamPlayerTeamDtos == null || dreamPlayerTeamDtos.isEmpty()) {
            throw new IllegalArgumentException("Dream player team list cannot be null or empty");
        }

        // Validate all DTOs before processing
        dreamPlayerTeamDtos.forEach(dto -> {
            try {
                dto.validate();
            } catch (IllegalArgumentException e) {
                logger.error("Invalid DTO: {}", dto);
                throw new IllegalArgumentException("Invalid dream player team data: " + e.getMessage());
            }
        });

        List<DreamPlayerTeam> entities = dreamPlayerTeamMapper.toEntityList(dreamPlayerTeamDtos);
        
        entities.forEach(entity -> {
            try {
                Match match = matchRepository.findById(Long.valueOf(entity.getMatch().getMatchId()))
                    .orElseThrow(() -> new RuntimeException("Match not found with ID: " + entity.getMatch().getMatchId()));
                Player player = playerRepository.findById(Long.valueOf(entity.getPlayer().getPlayerId()))
                    .orElseThrow(() -> new RuntimeException("Player not found with ID: " + entity.getPlayer().getPlayerId()));
                
                entity.setMatch(match);
                entity.setPlayer(player);
            } catch (Exception e) {
                logger.error("Error processing entity: {}", entity);
                throw new RuntimeException("Failed to process dream player team: " + e.getMessage());
            }
        });

        try {
            dreamPlayerTeamRepository.saveAll(entities);
        } catch (Exception e) {
            logger.error("Error saving entities", e);
            throw new RuntimeException("Failed to save dream player teams: " + e.getMessage());
        }
    }

    @Override
    public List<DreamPlayerTeamDto> getByMatch(Integer matchNo) {
        List<Object[]> results = dreamPlayerTeamRepository.findByMatchMatchId(matchNo);
        return dreamPlayerTeamMapper.mapFromQueryResults(results);
    }
}