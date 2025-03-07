package in.cricguru.service.impl;

import in.cricguru.dto.PlayerDto;
import in.cricguru.entity.Player;
import in.cricguru.exception.ResourceNotFoundException;
import in.cricguru.mapper.PlayerMapper;
import in.cricguru.repository.PlayerRepository;
import in.cricguru.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;

    @Override
    public List<PlayerDto> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        List<PlayerDto> playerDtos = players.stream()
                .map((player) -> PlayerMapper.mapToPlayerDto(player))
                .collect(Collectors.toList());
        return playerDtos;
    }

    @Override
    public PlayerDto createPlayer(PlayerDto playerDto) {
        Player player = PlayerMapper.mapToPlayer(playerDto);
        Player savedPlayer = playerRepository.save(player);
        return PlayerMapper.mapToPlayerDto(savedPlayer);
    }

    @Override
    public PlayerDto getPlayerById(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Player does not exist with id: " + playerId));
        PlayerDto playerDto = PlayerMapper.mapToPlayerDto(player);
        return playerDto;
    }

    @Override
    public PlayerDto updatePlayer(Long playerId, PlayerDto playerDto) {
        Player existingPlayer = playerRepository.findById(playerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Player does not exist with id: " + playerId));
        existingPlayer.setPlayerName(playerDto.getPlayerName());
        existingPlayer.setNickName(playerDto.getNickName());
        existingPlayer.setPlayerImgUrl(playerDto.getPlayerImgUrl());
        existingPlayer.setRole(playerDto.getRole());
        existingPlayer.setBattingStyle(playerDto.getBattingStyle());
        existingPlayer.setBowlingStyle(playerDto.getBowlingStyle());
        existingPlayer.setCountry(playerDto.getCountry());
        playerRepository.save(existingPlayer);
        return PlayerMapper.mapToPlayerDto(existingPlayer);
    }

    @Override
    public void deletePlayer(Long playerId) {
        Player existingPlayer = playerRepository.findById(playerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Player does not exist with id: " + playerId));
        playerRepository.deleteById(playerId);
    }
}