package in.cricguru.service;

import in.cricguru.dto.PlayerDto;

import java.util.List;

public interface PlayerService {
    List<PlayerDto> getAllPlayers();

    PlayerDto createPlayer(PlayerDto player);

    PlayerDto getPlayerById(Long playerId);

    PlayerDto updatePlayer(Long playerId, PlayerDto playerDto);

    void deletePlayer(Long playerId);
}