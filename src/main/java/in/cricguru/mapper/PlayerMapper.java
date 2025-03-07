package in.cricguru.mapper;

import in.cricguru.dto.PlayerDto;
import in.cricguru.entity.Player;

public class PlayerMapper {

    public static PlayerDto mapToPlayerDto(Player player) {
        return new PlayerDto(
                player.getPlayerId(),
                player.getPlayerName(),
                player.getNickName(),
                player.getPlayerImgUrl(),
                player.getRole(),
                player.getBattingStyle(),
                player.getBowlingStyle(),
                player.getCountry()
        );
    }

    public static Player mapToPlayer(PlayerDto playerDto) {
        return new Player(
                playerDto.getPlayerId(),
                playerDto.getPlayerName(),
                playerDto.getNickName(),
                playerDto.getPlayerImgUrl(),
                playerDto.getRole(),
                playerDto.getBattingStyle(),
                playerDto.getBowlingStyle(),
                playerDto.getCountry()
        );
    }
}