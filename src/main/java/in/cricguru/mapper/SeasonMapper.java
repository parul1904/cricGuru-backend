package in.cricguru.mapper;

import in.cricguru.dto.SeasonDto;
import in.cricguru.entity.Season;

public class SeasonMapper {

    public static SeasonDto mapToSeasonDto(Season season) {
        return new SeasonDto(
                season.getSeasonId(),
                season.getYear()
        );
    }

    public static Season mapToSeason(SeasonDto seasonDto) {
        return new Season(
                seasonDto.getSeasonId(),
                seasonDto.getYear()
        );
    }
}