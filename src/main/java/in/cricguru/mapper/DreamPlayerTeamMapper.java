package in.cricguru.mapper;

import in.cricguru.dto.DreamPlayerTeamDto;
import in.cricguru.entity.DreamPlayerTeam;
import in.cricguru.entity.DreamPlayerTeamId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
        if (dto == null || dto.getMatchNo() == null || dto.getPlayerId() == null) {
            throw new IllegalArgumentException("Both matchNo and playerId must be non-null");
        }
        return new DreamPlayerTeamId(
            dto.getMatchNo(),
            Math.toIntExact(dto.getPlayerId())
        );
    }

    @Named("mapFromQueryResults")
    default List<DreamPlayerTeamDto> mapFromQueryResults(List<Object[]> results) {
        if (results == null) {
            return new ArrayList<>();
        }

        return results.stream()
            .map(row -> DreamPlayerTeamDto.builder()
                .playerId(((Number) row[0]).longValue())
                .playerName((String) row[1])
                .playerRole((String) row[2])
                .teamShortName((String) row[3])
                .playing11(row[4] != null ? (Boolean) row[4] : false)
                .playing15(row[5] != null ? (Boolean) row[5] : false)
                .isCaptain(row[6] != null ? (Boolean) row[6] : false)
                .isViceCaptain(row[7] != null ? (Boolean) row[7] : false)
                .dreamTeam(row[8] != null ? (Boolean) row[8] : false)
                .selectionPercentage(row[9] != null ? ((Number) row[9]).doubleValue() : 0.0)
                .build())
            .collect(Collectors.toList());
    }
}
