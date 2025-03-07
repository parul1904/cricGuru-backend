package in.cricguru.repository;

import in.cricguru.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query(value = "SELECT * FROM players", nativeQuery = true)
    List<Player> findAllPlayers();
    // all crud database methods
}