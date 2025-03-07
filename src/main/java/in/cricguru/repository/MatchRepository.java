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

    @Query(value = """
            SELECT  m.match_id AS matchId, s.year AS seasonYear,  m.match_no AS matchNo,  t1.team_logo_url AS team1,  t2.team_logo_url AS team2,
            v.venue_name AS venueName,  m.match_date AS matchDate,  m.match_time AS matchTime, wt.team_logo_url AS winnerTeam,
            m.winning_margin AS winningMargin, p1.nick_name AS playerOfTheMatch, p2.nick_name AS mvp
            FROM matches m JOIN seasons s ON m.season_id = s.season_id
            JOIN teams t1 ON m.team1_id = t1.team_id JOIN teams t2 ON m.team2_id = t2.team_id
            JOIN venues v ON m.venue_id = v.venue_id LEFT JOIN teams wt ON m.winner_team_id = wt.team_id
            LEFT JOIN  players p1 ON m.player_of_the_match = p1.player_id
            LEFT JOIN  players p2 ON m.mvp_id = p2.player_id
        """, nativeQuery = true)
    List<Object[]> getAllMatchDetails();
}