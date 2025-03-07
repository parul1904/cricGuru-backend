package in.cricguru.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VenueDto {
    private Integer venueId;
    private String venueName;
    private String city;
    private String country;
    private String capacity;
    private String venueImageUrl;
}