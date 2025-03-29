package in.cricguru.repository;

import in.cricguru.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

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

    @Query(value = """
            SELECT m.match_id AS matchId, s.year AS seasonYear, m.match_no AS matchNo, 
                   t1.team_logo_url AS team1, t2.team_logo_url AS team2,
                   v.venue_name AS venueName, m.match_date AS matchDate, m.match_time AS matchTime, 
                   wt.team_logo_url AS winnerTeam, m.winning_margin AS winningMargin, 
                   p1.nick_name AS playerOfTheMatch, p2.nick_name AS mvp
            FROM matches m 
            JOIN seasons s ON m.season_id = s.season_id
            JOIN teams t1 ON m.team1_id = t1.team_id 
            JOIN teams t2 ON m.team2_id = t2.team_id
            JOIN venues v ON m.venue_id = v.venue_id 
            LEFT JOIN teams wt ON m.winner_team_id = wt.team_id
            LEFT JOIN players p1 ON m.player_of_the_match = p1.player_id
            LEFT JOIN players p2 ON m.mvp_id = p2.player_id
            WHERE s.year = :seasonYear
            ORDER BY 
                CASE 
                    WHEN m.match_date >= CURRENT_DATE THEN 0 
                    ELSE 1 
                END,
                CASE 
                    WHEN m.match_date >= CURRENT_DATE THEN m.match_no 
                    ELSE NULL 
                END ASC,
                CASE 
                    WHEN m.match_date < CURRENT_DATE THEN m.match_no 
                    ELSE NULL 
                END DESC,
                STR_TO_DATE(CONCAT(m.match_date, ' ', m.match_time), '%Y-%m-%d %h:%i %p') ASC
            """, nativeQuery = true)
    List<Object[]> getAllMatchDetailsBySeason(Integer seasonYear);

    @Query("SELECT m FROM Match m " +
            "WHERE m.matchDate >= :now " +
            "ORDER BY m.matchDate ASC, m.matchTime ASC")
    List<Match> findUpcomingMatches(@Param("now") LocalDate now, Pageable pageable);

    @Query(value = """
            SELECT m.* FROM matches m WHERE STR_TO_DATE(CONCAT(m.match_date, ' ', SUBSTRING_INDEX(m.match_time, ' ', 2)), '%Y-%m-%d %h:%i %p')\s
            >= CONVERT_TZ(:now, @@session.time_zone, '+05:30')
            ORDER BY m.match_date ASC, STR_TO_DATE(SUBSTRING_INDEX(m.match_time, ' ', 2), '%h:%i %p') ASC LIMIT 1
            """, nativeQuery = true)
    Match nextMatch(@Param("now") LocalDate now);

    @Query("FROM Match m WHERE m.season.seasonId = :seasonId AND m.matchNo = :matchNo")
    Optional<Match> findBySeasonIdAndMatchNo(@Param("seasonId") Integer seasonId, @Param("matchNo") Integer matchNo);
}
