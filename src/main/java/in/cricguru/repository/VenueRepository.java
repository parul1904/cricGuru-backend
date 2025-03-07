package in.cricguru.repository;

import in.cricguru.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Long> {
    // all crud database methods
}