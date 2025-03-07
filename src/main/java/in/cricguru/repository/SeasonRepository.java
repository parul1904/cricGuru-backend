package in.cricguru.repository;

import in.cricguru.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season, Long> {
    // all crud database methods
}