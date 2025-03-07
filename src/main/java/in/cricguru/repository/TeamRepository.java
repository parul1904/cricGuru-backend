package in.cricguru.repository;

import in.cricguru.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    // all crud database methods
}