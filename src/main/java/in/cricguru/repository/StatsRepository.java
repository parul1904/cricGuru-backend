package in.cricguru.repository;

import in.cricguru.entity.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatsRepository extends JpaRepository<Stats, Long> {

    @Query(value = "SELECT * FROM cricguru_db.match_stats s WHERE s.match_no = :matchId ORDER BY s.total_point_dream11_old_system DESC", nativeQuery = true)
    List<Object[]> getAllStatsByMatchId(@Param("matchId") Long matchId);

    @Query(value = """
            SELECT ms.match_stats_id, s.year, m.match_no, p.player_name, p.player_img_url, p.role, p.country,
            ms.runs_scored, ms.ball_faced, ms.fours, ms.sixes, ms.strike_rate, ms.overs, ms.total_wickets, ms.runs_conceded, ms.economy_rate,
            ms.catch_taken, ms.stumping, ms.is_impact_player
            FROM players p LEFT JOIN match_stats ms ON p.player_id = ms.player_id
            LEFT JOIN seasons s ON ms.season_id = s.season_id
            LEFT JOIN matches m ON ms.match_no = m.match_id
            WHERE p.player_id = :playerId
            """, nativeQuery = true)
    List<Object[]>
    getPlayerStatsByPlayerId(@Param("playerId") Long playerId);

    @Query(value = """
            SELECT p.player_id, p.player_img_url, p.nick_name, p.role, t1.team_logo_url AS team1_id,\s
            t2.team_logo_url AS team2_id, ms.total_point_dream11_old_system, ms.total_point_my11_circle_system,\s
            ms.total_point_dream11_new_system FROM match_stats ms
            JOIN players p ON ms.player_id = p.player_id JOIN matches m ON ms.match_no = m.match_id
            JOIN teams t1 ON m.team1_id = t1.team_id  JOIN teams t2 ON m.team2_id = t2.team_id
            WHERE ms.match_no = :matchNo order by ms.total_point_dream11_old_system desc limit 14
            """, nativeQuery = true)
    List<Object[]> getDreamTeamByMatchNo(@Param("matchNo") Long matchNo);

    @Query(value = """
            SELECT ms.match_stats_id AS id, s.year AS seasonYear, t1.team_logo_url AS team1, t2.team_logo_url AS team2,
            m.match_date AS matchDate, p.player_name AS playerName, ms.total_point_dream11_old_system AS dream11Points
            FROM match_stats ms JOIN seasons s ON ms.season_id = s.season_id
            JOIN matches m ON ms.match_no = m.match_id JOIN players p ON ms.player_id = p.player_id
            JOIN teams t1 ON m.team1_id = t1.team_id JOIN teams t2 ON m.team2_id = t2.team_id
            """, nativeQuery = true)
    List<Object[]> getAllStats();
}