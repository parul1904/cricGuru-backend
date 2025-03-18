package in.cricguru.repository;

import in.cricguru.entity.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatsRepository extends JpaRepository<Stats, Long> {

    @Query(value = """
            SELECT p.player_id, p.player_name, p.player_img_url, ms.runs_scored, ms.fours, ms.sixes AS total_sixes, ms.strike_rate,
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
            SELECT t.team_short_name, SUM(ms.runs_scored) AS total_runs, SUM(ms.ball_faced) AS total_ball_faced,
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
            GROUP BY t.team_short_name""", nativeQuery = true)
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
            SELECT ms.match_stats_id AS id, s.year AS seasonYear, m.match_no, t1.team_logo_url AS team1, t2.team_logo_url AS team2,
            m.match_date AS matchDate, p.player_name AS playerName, ms.total_point_dream11_old_system AS dream11Points
            FROM match_stats ms JOIN seasons s ON ms.season_id = s.season_id
            JOIN matches m ON ms.match_no = m.match_id JOIN players p ON ms.player_id = p.player_id
            JOIN teams t1 ON m.team1_id = t1.team_id JOIN teams t2 ON m.team2_id = t2.team_id ORDER BY ms.match_stats_id DESC
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

    @Query(value = """
            WITH CurrentSquadPlayers AS (
                SELECT DISTINCT p.player_id, p.nick_name, p.player_img_url, p.role, p.batting_style, p.bowling_style
                FROM players p
                JOIN squads sq ON p.player_id = sq.player_id
                WHERE sq.season_id = :seasonId
                AND sq.team_id IN (:team1Id, :team2Id)
            ),
            DreamTeamRanks AS (
                SELECT match_no, player_id,
                CASE
                    WHEN ROW_NUMBER() OVER (PARTITION BY match_no ORDER BY total_point_dream11_new_system DESC) <= 11
                    THEN true
                    ELSE false
                END as is_in_dream_team
                FROM match_stats
            ),
            LastMatchStats AS (
                SELECT player_id, total_point_dream11_new_system as last_match_points, match_no as last_match_no
                FROM (
                    SELECT player_id, total_point_dream11_new_system, match_no, ROW_NUMBER() OVER (PARTITION BY player_id ORDER BY match_no DESC) as rn
                    FROM match_stats
                ) ranked WHERE rn = 1
            ),
            PlayerMatchStats AS (
                SELECT ms.player_id, ms.match_no, m.match_date, t1.team_short_name as team1_name, t1.team_logo_url as team1_logo,
                t2.team_short_name as team2_name, t2.team_logo_url as team2_logo, ms.total_point_dream11_new_system as points, ms.runs_scored, ms.ball_faced,
                ms.fours, ms.sixes, ms.catch_taken, ms.stumping, ms.direct_runout, ms.in_direct_runout, ms.total_wickets, ms.overs, ms.runs_conceded, dt.is_in_dream_team, 
                ms.total_point_dream11_old_system, ms.total_point_my11_circle_system, ms.total_point_dream11_new_system,
                ROW_NUMBER() OVER (PARTITION BY ms.player_id ORDER BY ms.match_no DESC) as match_rank,
                AVG(ms.total_point_dream11_new_system) OVER (PARTITION BY ms.player_id) as avg_points,
                MAX(ms.total_point_dream11_new_system) OVER (PARTITION BY ms.player_id) as highest_points,
                MIN(ms.total_point_dream11_new_system) OVER (PARTITION BY ms.player_id) as lowest_points,
                AVG(ms.total_point_dream11_new_system) OVER (PARTITION BY ms.player_id ORDER BY ms.match_no DESC ROWS BETWEEN 2 PRECEDING AND CURRENT ROW) as avg_last_3_points
                FROM match_stats ms 
                JOIN matches m ON ms.match_no = m.match_id 
                JOIN teams t1 ON m.team1_id = t1.team_id 
                JOIN teams t2 ON m.team2_id = t2.team_id
                LEFT JOIN DreamTeamRanks dt ON ms.match_no = dt.match_no AND ms.player_id = dt.player_id
            )
            SELECT csp.player_id, csp.nick_name, csp.player_img_url, csp.role, csp.batting_style, csp.bowling_style, 
            AVG(pms.avg_points) as avg_points, MAX(pms.highest_points) as highest_points, MIN(pms.lowest_points) as lowest_points, 
            lms.last_match_points, lms.last_match_no, AVG(pms.avg_last_3_points) as avg_last_3_points, 
            JSON_ARRAYAGG(JSON_OBJECT(
                'matchNo', pms.match_no, 'matchDate', pms.match_date, 'team1Name', pms.team1_name, 'team1Logo', pms.team1_logo, 'team2Name', pms.team2_name,
                'team2Logo', pms.team2_logo, 'points', pms.points, 'runsScored', pms.runs_scored, 'ballFaced', pms.ball_faced, 'fours', pms.fours,
                'sixes', pms.sixes, 'catches', pms.catch_taken, 'stumpings', pms.stumping, 'runOutDirect', pms.direct_runout, 'runOutInDirect', pms.in_direct_runout,
                'wickets', pms.total_wickets, 'overs', pms.overs, 'runsConceded', pms.runs_conceded, 'isPartOfDreamTeam', pms.is_in_dream_team,
                'dream11OldPoints', pms.total_point_dream11_old_system, 'my11CirclePoints', pms.total_point_my11_circle_system, 'dream11NewPoints', pms.total_point_dream11_new_system)
            ) as match_details
            FROM CurrentSquadPlayers csp 
            LEFT JOIN PlayerMatchStats pms ON csp.player_id = pms.player_id AND pms.match_rank <= :statsBy
            LEFT JOIN LastMatchStats lms ON csp.player_id = lms.player_id
            GROUP BY csp.player_id, csp.nick_name, csp.player_img_url, csp.role, csp.batting_style, csp.bowling_style, lms.last_match_points, lms.last_match_no
            ORDER BY lms.last_match_no DESC, pms.match_no DESC;
            """, nativeQuery = true)
    List<Object[]> getPlayerPerformanceStats(@Param("seasonId") Long seasonId, @Param("team1Id") Long team1Id, @Param("team2Id") Long team2Id, @Param("statsBy") Long statsBy);

    @Query(value = """
            WITH MatchPlayers AS (
                SELECT DISTINCT p.player_id, p.nick_name, p.player_img_url, p.role, p.batting_style, p.bowling_style
                FROM players p
                JOIN match_stats ms ON p.player_id = ms.player_id
                WHERE ms.match_no = :matchNo
            ),
            DreamTeamRanks AS (
                SELECT match_no, player_id,
                CASE
                    WHEN ROW_NUMBER() OVER (PARTITION BY match_no ORDER BY total_point_dream11_new_system DESC) <= 11
                    THEN true
                    ELSE false
                END as is_in_dream_team
                FROM match_stats
            ),
            LastMatchStats AS (
                SELECT player_id, total_point_dream11_new_system as last_match_points, match_no as last_match_no
                FROM (
                    SELECT player_id, total_point_dream11_new_system, match_no,
                    ROW_NUMBER() OVER (PARTITION BY player_id ORDER BY match_no DESC) as rn
                    FROM match_stats
                ) ranked WHERE rn = 1
            ),
            PlayerMatchStats AS (
                SELECT ms.player_id, ms.match_no, m.match_date,
                    t1.team_short_name as team1_name, t1.team_logo_url as team1_logo,
                    t2.team_short_name as team2_name, t2.team_logo_url as team2_logo,
                    ms.total_point_dream11_new_system as points, ms.runs_scored, ms.ball_faced,
                    ms.fours, ms.sixes, ms.catch_taken, ms.stumping, ms.direct_runout, ms.in_direct_runout, 
                    ms.total_wickets, ms.overs, ms.runs_conceded, dt.is_in_dream_team,
                    ROW_NUMBER() OVER (PARTITION BY ms.player_id ORDER BY ms.match_no DESC) as match_rank,
                    AVG(ms.total_point_dream11_new_system) OVER (PARTITION BY ms.player_id) as avg_points,
                    MAX(ms.total_point_dream11_new_system) OVER (PARTITION BY ms.player_id) as highest_points,
                    MIN(ms.total_point_dream11_new_system) OVER (PARTITION BY ms.player_id) as lowest_points
                FROM match_stats ms
                JOIN matches m ON ms.match_no = m.match_id
                JOIN teams t1 ON m.team1_id = t1.team_id
                JOIN teams t2 ON m.team2_id = t2.team_id
                LEFT JOIN DreamTeamRanks dt ON ms.match_no = dt.match_no AND ms.player_id = dt.player_id
            )
            SELECT 
                mp.player_id, mp.nick_name, mp.player_img_url, mp.role,
                mp.batting_style, mp.bowling_style,
                pms.avg_points, pms.highest_points, pms.lowest_points,
                lms.last_match_points, lms.last_match_no,
                JSON_ARRAYAGG(
                    JSON_OBJECT(
                        'matchNo', pms.match_no,
                        'matchDate', pms.match_date,
                        'team1Name', pms.team1_name,
                        'team1Logo', pms.team1_logo,
                        'team2Name', pms.team2_name,
                        'team2Logo', pms.team2_logo,
                        'points', pms.points,
                        'runsScored', pms.runs_scored,
                        'ballFaced', pms.ball_faced,
                        'fours', pms.fours,
                        'sixes', pms.sixes,
                        'catches', pms.catch_taken,
                        'stumpings', pms.stumping,
                        'runOutDirect', pms.direct_runout,
                        'runOutInDirect', pms.in_direct_runout,
                        'wickets', pms.total_wickets,
                        'overs', pms.overs,
                        'runsConceded', pms.runs_conceded,
                        'isPartOfDreamTeam', pms.is_in_dream_team
                    )
                ) as match_details
            FROM MatchPlayers mp
            LEFT JOIN PlayerMatchStats pms ON mp.player_id = pms.player_id AND pms.match_rank <= 5
            LEFT JOIN LastMatchStats lms ON mp.player_id = lms.player_id
            GROUP BY 
                mp.player_id, mp.nick_name, mp.player_img_url, mp.role,
                mp.batting_style, mp.bowling_style,
                pms.avg_points, pms.highest_points, pms.lowest_points,
                lms.last_match_points, lms.last_match_no
            ORDER BY lms.last_match_no DESC, pms.match_no DESC
            """, nativeQuery = true)
    List<Object[]> getPlayerPerformanceStats(@Param("matchNo") Long matchNo);

}
