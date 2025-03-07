package in.cricguru.service.impl;

import in.cricguru.dto.SeasonDto;
import in.cricguru.entity.Season;
import in.cricguru.exception.ResourceNotFoundException;
import in.cricguru.mapper.SeasonMapper;
import in.cricguru.repository.SeasonRepository;
import in.cricguru.service.SeasonService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SeasonServiceImpl implements SeasonService {

    private SeasonRepository seasonRepository;

    @Cacheable(cacheNames="allSeasons", key = "#seasonId")
    @Override
    public List<SeasonDto> getAllSeasons() {
        List<Season> seasons = seasonRepository.findAll();
        List<SeasonDto> seasonDtos = seasons.stream()
                .map((season) -> SeasonMapper.mapToSeasonDto(season))
                .collect(Collectors.toList());
        return seasonDtos;
    }

    @Override
    public SeasonDto getSeasonById(Long seasonId) {
        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Season does not exist with id: " + seasonId));
        SeasonDto seasonDto = SeasonMapper.mapToSeasonDto(season);
        return seasonDto;
    }

}