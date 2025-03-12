package in.cricguru.controller;

import in.cricguru.dto.SeasonDto;
import in.cricguru.service.SeasonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static in.cricguru.shared.CricGuruConstant.BASE_URL;

@AllArgsConstructor
@RestController
@RequestMapping("/seasons")
@CrossOrigin(origins = BASE_URL)
public class SeasonController {

    private SeasonService seasonService;

    @GetMapping
    public ResponseEntity<List<SeasonDto>> getAllSeasons(){
        List<SeasonDto> seasons = seasonService.getAllSeasons();
        return ResponseEntity.ok(seasons);
    }


    // build get Player by id REST API
    @GetMapping("{id}")
    public ResponseEntity<SeasonDto> getSeasonById(@PathVariable("id") Long seasonId){
        SeasonDto season = seasonService.getSeasonById(seasonId);
        return ResponseEntity.ok(season);
    }

}