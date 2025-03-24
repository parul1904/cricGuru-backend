package in.cricguru.mapper;

import in.cricguru.dto.DreamPlayerTeamDto;
import in.cricguru.entity.DreamPlayerTeam;
import in.cricguru.entity.DreamPlayerTeamId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface DreamPlayerTeamMapper {
    
    @Mapping(target = "id", source = ".", qualifiedByName = "createId")
    @Mapping(source = "matchNo", target = "match.matchId")
    @Mapping(source = "playerId", target = "player.playerId")
    DreamPlayerTeam toEntity(DreamPlayerTeamDto dto);

    @Mapping(source = "match.matchId", target = "matchNo")
    @Mapping(source = "player.playerId", target = "playerId")
    DreamPlayerTeamDto toDto(DreamPlayerTeam entity);

    List<DreamPlayerTeam> toEntityList(List<DreamPlayerTeamDto> dtos);
    List<DreamPlayerTeamDto> toDtoList(List<DreamPlayerTeam> entities);

    @Named("createId")
    default DreamPlayerTeamId createId(DreamPlayerTeamDto dto) {
        return new DreamPlayerTeamId(dto.getMatchNo(), Math.toIntExact(dto.getPlayerId()));
    }
}
