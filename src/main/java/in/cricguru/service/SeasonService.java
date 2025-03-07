package in.cricguru.service;

import in.cricguru.dto.SeasonDto;

import java.util.List;

public interface SeasonService {
    List<SeasonDto> getAllSeasons();


    SeasonDto getSeasonById(Long seasonId);

}