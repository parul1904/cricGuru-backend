package in.cricguru.mapper;

import in.cricguru.dto.VenueDto;
import in.cricguru.entity.Venue;

public class VenueMapper {

    public static VenueDto mapToVenueDto(Venue venue) {
        return new VenueDto(
                venue.getVenueId(),
                venue.getVenueName(),
                venue.getCity(),
                venue.getCountry(),
                venue.getCapacity(),
                venue.getVenueImageUrl()
        );
    }

    public static Venue mapToVenue(VenueDto venueDto) {
        return new Venue(
                venueDto.getVenueId(),
                venueDto.getVenueName(),
                venueDto.getCity(),
                venueDto.getCountry(),
                venueDto.getCapacity(),
                venueDto.getVenueImageUrl()
        );
    }
}