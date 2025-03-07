package in.cricguru.controller;

import in.cricguru.dto.SeasonDto;
import in.cricguru.service.SeasonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/seasons")
@CrossOrigin(origins = "http://192.168.1.114:3000")
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