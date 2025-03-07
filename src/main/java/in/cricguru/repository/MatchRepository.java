package in.cricguru.repository;

import in.cricguru.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {

    @Query("FROM Match m WHERE m.season.seasonId = :seasonId ORDER BY m.matchNo ASC")
    List<Match> findAllBySeasonId(@Param("seasonId") Long seasonId);

    @Query("SELECT t1.teamLogoUrl AS team1LogoUrl, t2.teamLogoUrl AS team2LogoUrl, m.matchDate "
            + "FROM Match m "
            + "JOIN m.team1 t1 "
            + "JOIN m.team2 t2 "
            + "WHERE m.matchId = :matchId ORDER BY m.matchNo ASC")
    List<Object[]> getMatchDetailsByMatchId(@Param("matchId") Long matchId);
}