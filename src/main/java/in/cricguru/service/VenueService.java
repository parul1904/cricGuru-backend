package in.cricguru.service;

import in.cricguru.dto.SquadDto;
import in.cricguru.dto.VenueDto;
import in.cricguru.response.SquadResponse;
import in.cricguru.response.SquadTeamResponse;

import java.util.List;

public interface VenueService {
    List<VenueDto> getAllVenues();

    VenueDto createVenue(VenueDto venue);

    VenueDto getVenueById(Long venueId);

    VenueDto updateVenue(Long venueId, VenueDto venueDto);

    void deleteVenue(Long venueId);



}