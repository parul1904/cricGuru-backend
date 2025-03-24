package in.cricguru.service.impl;

import in.cricguru.dto.DreamPlayerTeamDto;
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

import java.util.List;

@Service
@RequiredArgsConstructor
public class DreamPlayerTeamServiceImpl implements DreamPlayerTeamService {

    private final DreamPlayerTeamRepository dreamPlayerTeamRepository;
    private final DreamPlayerTeamMapper dreamPlayerTeamMapper;
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;

    @Override
    @Transactional
    public void saveBatch(List<DreamPlayerTeamDto> dreamPlayerTeamDtos) {
        List<DreamPlayerTeam> entities = dreamPlayerTeamMapper.toEntityList(dreamPlayerTeamDtos);
        
        entities.forEach(entity -> {
            Match match = matchRepository.findById(Long.valueOf(entity.getMatch().getMatchId()))
                .orElseThrow(() -> new RuntimeException("Match not found"));
            Player player = playerRepository.findById(Long.valueOf(entity.getPlayer().getPlayerId()))
                .orElseThrow(() -> new RuntimeException("Player not found"));
            
            entity.setMatch(match);
            entity.setPlayer(player);
        });

        dreamPlayerTeamRepository.saveAll(entities);
    }

    @Override
    public List<DreamPlayerTeamDto> getByMatch(Integer matchNo) {
        List<DreamPlayerTeam> dreamPlayerTeams = dreamPlayerTeamRepository.findByMatchMatchId(matchNo);
        return dreamPlayerTeamMapper.toDtoList(dreamPlayerTeams);
    }
}