package in.cricguru.repository;

import in.cricguru.entity.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatsRepository extends JpaRepository<Stats, Long> {

    @Query(value = """
            SELECT p.player_name, p.player_img_url, ms.runs_scored, ms.fours, ms.sixes AS total_sixes, ms.strike_rate,
            ms.catch_taken, ms.overs , ms.total_wickets, ms.runs_conceded, ms.dots,
            ms.runs_conceded / NULLIF((SUM(FLOOR(ms.overs)) + SUM(ms.overs % 1 * 10) / 6), 0) AS economy_rate,
            ms.total_point_dream11_old_system, ms.total_point_dream11_new_system, ms.total_point_my11_circle_system
            FROM match_stats ms LEFT JOIN players p ON ms.player_id = p.player_id
            LEFT JOIN matches m ON ms.match_no = m.match_id
            LEFT JOIN seasons s ON ms.season_id = s.season_id
            WHERE  ms.match_no=:matchId GROUP BY p.player_id
            ORDER BY  ms.total_point_dream11_new_system DESC
            """, nativeQuery = true)
    List<Object[]> getAllStatsByMatchId(@Param("matchId") Long matchId);

    @Query(value = """
            SELECT t.team_name, SUM(ms.runs_scored) AS total_runs, SUM(ms.ball_faced) AS total_ball_faced,
            SUM(ms.fours) AS total_fours, SUM(ms.sixes) AS total_sixes, AVG(ms.strike_rate) AS total_strike_rate,
            SUM(CASE WHEN ms.runs_scored >= 50 AND ms.runs_scored < 100 THEN 1 ELSE 0 END) AS fifties, SUM(CASE WHEN ms.runs_scored >= 100 THEN 1 ELSE 0 END) AS hundreds,
            SUM(ms.catch_taken) AS total_catches, SUM(ms.stumping) AS total_stumping, SUM(CASE WHEN ms.overs > 0 THEN 1 ELSE 0 END) AS bowling_innings_played,
            SUM(FLOOR(ms.overs)) + FLOOR(SUM(ms.overs % 1 * 10) / 6) AS total_overs, (SELECT SUM(COALESCE(ms.total_wickets, 0)) + SUM(COALESCE(ms.direct_runout, 0)) 
            + SUM(COALESCE(ms.in_direct_runout, 0) / 2)) AS total_wickets, SUM(ms.runs_conceded) AS total_runs_conceded,
            SUM(ms.dots) AS total_dots, SUM(ms.maiden) AS total_maidens, SUM(ms.runs_conceded) / NULLIF(SUM(ms.total_wickets), 0) AS bowling_average,
            SUM(ms.runs_conceded) / NULLIF((SUM(FLOOR(ms.overs)) + SUM(ms.overs % 1 * 10) / 6), 0) AS economy_rate
            FROM teams t JOIN squads s ON t.team_id = s.team_id
            JOIN match_stats ms ON s.player_id = ms.player_id AND s.season_id = ms.season_id
            WHERE t.team_id=:teamId
            GROUP BY t.team_name""", nativeQuery = true)
    List<Object[]> getAllStatsByTeamId(@Param("teamId") Long teamId);

    @Query(value = """
            SELECT SUM(CASE WHEN ball_faced > 0 THEN 1 ELSE 0 END) AS batting_innings_played, SUM(ms.runs_scored) AS total_runs, SUM(ms.ball_faced) AS total_ball_faced,\s
            SUM(ms.fours) AS total_fours, SUM(ms.sixes) AS total_sixes, AVG(ms.strike_rate) AS total_strike_rate,\s
            SUM(CASE WHEN ms.runs_scored >= 50 AND ms.runs_scored < 100 THEN 1 ELSE 0 END) AS fifties, SUM(CASE WHEN ms.runs_scored >= 100 THEN 1 ELSE 0 END) AS hundreds,\s
            SUM(ms.catch_taken) AS total_catches, SUM(ms.stumping) AS total_stumping, SUM(CASE WHEN ms.overs > 0 THEN 1 ELSE 0 END) AS bowling_innings_played,\s
            SUM(FLOOR(ms.overs)) + FLOOR(SUM(ms.overs % 1 * 10) / 6) AS total_overs, (SELECT SUM(COALESCE(ms.total_wickets, 0)) + SUM(COALESCE(ms.direct_runout, 0)) 
            + SUM(COALESCE(ms.in_direct_runout, 0) / 2)) AS total_wickets, SUM(ms.runs_conceded) AS total_runs_conceded,\s
            SUM(ms.dots) AS total_dots, SUM(ms.maiden) AS total_maidens, SUM(ms.runs_conceded) / NULLIF(SUM(ms.total_wickets), 0) AS bowling_average,\s
            SUM(ms.runs_conceded) / NULLIF((SUM(FLOOR(ms.overs)) + SUM(ms.overs % 1 * 10) / 6), 0) AS economy_rate,
            (SELECT COUNT(*) FROM match_stats ms WHERE ms.player_id = :playerId) AS total_matches
            FROM match_stats ms LEFT JOIN players p ON ms.player_id = p.player_id\s
            LEFT JOIN matches m ON ms.match_no = m.match_id
            LEFT JOIN seasons s ON ms.season_id = s.season_id
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
            WHERE ms.match_no = :matchNo order by ms.total_point_dream11_old_system desc limit 11
            """, nativeQuery = true)
    List<Object[]> getOldDreamTeamByMatchNo(@Param("matchNo") Long matchNo);

    @Query(value = """
            SELECT p.player_id, p.player_img_url, p.nick_name, p.role, t1.team_logo_url AS team1_id,\s
            t2.team_logo_url AS team2_id, ms.total_point_dream11_old_system, ms.total_point_my11_circle_system,\s
            ms.total_point_dream11_new_system FROM match_stats ms
            JOIN players p ON ms.player_id = p.player_id JOIN matches m ON ms.match_no = m.match_id
            JOIN teams t1 ON m.team1_id = t1.team_id  JOIN teams t2 ON m.team2_id = t2.team_id
            WHERE ms.match_no = :matchNo order by ms.total_point_dream11_new_system desc limit 11
            """, nativeQuery = true)
    List<Object[]> getNewDreamTeamByMatchNo(@Param("matchNo") Long matchNo);

    @Query(value = """
            SELECT p.player_id, p.player_img_url, p.nick_name, p.role, t1.team_logo_url AS team1_id,\s
            t2.team_logo_url AS team2_id, ms.total_point_dream11_old_system, ms.total_point_my11_circle_system,\s
            ms.total_point_dream11_new_system FROM match_stats ms
            JOIN players p ON ms.player_id = p.player_id JOIN matches m ON ms.match_no = m.match_id
            JOIN teams t1 ON m.team1_id = t1.team_id  JOIN teams t2 ON m.team2_id = t2.team_id
            WHERE ms.match_no = :matchNo order by ms.total_point_my11_circle_system desc limit 11
            """, nativeQuery = true)
    List<Object[]> getMy11CircleDreamTeamByMatchNo(@Param("matchNo") Long matchNo);

    @Query(value = """
            SELECT ms.match_stats_id AS id, s.year AS seasonYear, t1.team_logo_url AS team1, t2.team_logo_url AS team2,
            m.match_date AS matchDate, p.player_name AS playerName, ms.total_point_dream11_old_system AS dream11Points
            FROM match_stats ms JOIN seasons s ON ms.season_id = s.season_id
            JOIN matches m ON ms.match_no = m.match_id JOIN players p ON ms.player_id = p.player_id
            JOIN teams t1 ON m.team1_id = t1.team_id JOIN teams t2 ON m.team2_id = t2.team_id
            """, nativeQuery = true)
    List<Object[]> getAllStats();

    @Query(value = """
            SELECT SUM(CASE WHEN ball_faced > 0 THEN 1 ELSE 0 END) AS batting_innings_played, SUM(ms.runs_scored) AS total_runs, SUM(ms.ball_faced) AS total_ball_faced,\s
            SUM(ms.fours) AS total_fours, SUM(ms.sixes) AS total_sixes, AVG(ms.strike_rate) AS total_strike_rate,\s
            SUM(CASE WHEN ms.runs_scored >= 50 AND ms.runs_scored < 100 THEN 1 ELSE 0 END) AS fifties, SUM(CASE WHEN ms.runs_scored >= 100 THEN 1 ELSE 0 END) AS hundreds,\s
            SUM(ms.catch_taken) AS total_catches, SUM(ms.stumping) AS total_stumping, SUM(CASE WHEN ms.overs > 0 THEN 1 ELSE 0 END) AS bowling_innings_played,\s
            SUM(FLOOR(ms.overs)) + FLOOR(SUM(ms.overs % 1 * 10) / 6) AS total_overs, (SELECT SUM(COALESCE(ms.total_wickets, 0)) + SUM(COALESCE(ms.direct_runout, 0)) 
            + SUM(COALESCE(ms.in_direct_runout, 0) / 2)) AS total_wickets, SUM(ms.runs_conceded) AS total_runs_conceded,\s
            SUM(ms.dots) AS total_dots, SUM(ms.maiden) AS total_maidens, SUM(ms.runs_conceded) / NULLIF(SUM(ms.total_wickets), 0) AS bowling_average,\s
            SUM(ms.runs_conceded) / NULLIF((SUM(FLOOR(ms.overs)) + SUM(ms.overs % 1 * 10) / 6), 0) AS economy_rate,
            (SELECT COUNT(*) FROM match_stats ms WHERE ms.player_id = :venueId) AS total_matches
            FROM match_stats ms LEFT JOIN players p ON ms.player_id = p.player_id\s
            LEFT JOIN matches m ON ms.match_no = m.match_id
            LEFT JOIN seasons s ON ms.season_id = s.season_id
            WHERE p.player_id = :venueId
            """, nativeQuery = true)
    List<Object[]> getAllStatsByVenue(@Param("venueId") Long venueId);


}