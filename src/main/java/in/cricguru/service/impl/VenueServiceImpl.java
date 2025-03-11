package in.cricguru.service.impl;

import in.cricguru.dto.SquadDto;
import in.cricguru.dto.VenueDto;
import in.cricguru.entity.Squad;
import in.cricguru.entity.Venue;
import in.cricguru.exception.ResourceNotFoundException;
import in.cricguru.mapper.SquadMapper;
import in.cricguru.mapper.VenueMapper;
import in.cricguru.repository.SquadRepository;
import in.cricguru.repository.VenueRepository;
import in.cricguru.response.SquadResponse;
import in.cricguru.response.SquadTeamResponse;
import in.cricguru.service.SquadService;
import in.cricguru.service.VenueService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VenueServiceImpl implements VenueService {

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private VenueMapper venueMapper;

    @Cacheable(cacheNames="allVenues")
    @Override
    public List<VenueDto> getAllVenues() {
        List<Venue> venues = venueRepository.findAll();
        List<VenueDto> venueDto = venues.stream()
                .map((venue) -> venueMapper.mapToVenueDto(venue))
                .collect(Collectors.toList());
        return venueDto;
    }

    @Override
    public VenueDto createVenue(VenueDto venueDto) {
        Venue venue = venueMapper.mapToVenue(venueDto);
        Venue savedVenue = venueRepository.save(venue);
        return venueMapper.mapToVenueDto(savedVenue);
    }

    @Override
    public VenueDto getVenueById(Long venueId) {
        Venue squad = venueRepository.findById(venueId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Venue does not exist with id: " + venueId));
        VenueDto venueDto = venueMapper.mapToVenueDto(squad);
        return venueDto;
    }

    @Override
    public VenueDto updateVenue(Long venueId, VenueDto venueDto) {
        if (!venueRepository.existsById(venueId)) {
            throw new RuntimeException("Squad not found with id: " + venueId);
        }
        Venue existingVenue = venueRepository.findById(venueId)
                .orElseThrow();
        Venue updatedVenue = venueMapper.mapToVenue(venueDto);
        updatedVenue.setVenueId(existingVenue.getVenueId());
        Venue savedVenue = venueRepository.save(updatedVenue);
        return venueMapper.mapToVenueDto(savedVenue);
    }

    @Override
    public void deleteVenue(Long venueId) {
        Venue existingVenue = venueRepository.findById(venueId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Venue does not exist with id: " + venueId));
        venueRepository.deleteById(venueId);
    }


}