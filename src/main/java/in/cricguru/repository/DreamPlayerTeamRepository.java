package in.cricguru.repository;

import in.cricguru.entity.DreamPlayerTeam;
import in.cricguru.entity.DreamPlayerTeamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DreamPlayerTeamRepository extends JpaRepository<DreamPlayerTeam, DreamPlayerTeamId> {
    
    @Query(value = """
             SELECT p.player_id, p.player_name, p.role, p.player_img_url, ms.total_point_dream11_new_system
                        FROM dream_player_team dpt
                        LEFT JOIN players p ON dpt.player_id = p.player_id
                        LEFT JOIN match_stats ms ON dpt.player_id = ms.player_id\s
                            AND ms.match_no = dpt.match_no
                            AND ms.season_id = dpt.season_id
                        WHERE dpt.match_no = :matchNo
                            AND dpt.dream_team = 1
                        ORDER BY ms.total_point_dream11_new_system DESC
            """, nativeQuery = true)
    List<Object[]> findActualDreamTeamByMatchNo(@Param("matchNo") Integer matchNo);

    @Query(value = "SELECT DISTINCT match_no FROM dream_player_team WHERE dream_team = true", nativeQuery = true)
    List<Integer> findMatchesWithDreamTeam();
    
    @Query(value = """
            SELECT DISTINCT 
                p.player_id, 
                p.player_name, 
                p.role as player_role,
                t.team_short_name,
                dpt.playing_11,
                dpt.playing_15,
                dpt.is_captain,
                dpt.is_vice_captain,
                dpt.dream_team,
                dpt.selection_percentage
            FROM matches m
            JOIN teams t1 ON m.team1_id = t1.team_id
            JOIN teams t2 ON m.team2_id = t2.team_id
            JOIN squads s ON (s.team_id = t1.team_id OR s.team_id = t2.team_id)
            JOIN players p ON s.player_id = p.player_id
            JOIN teams t ON s.team_id = t.team_id
            LEFT JOIN dream_player_team dpt ON dpt.player_id = p.player_id AND dpt.match_no = m.match_id
            WHERE m.match_id = :matchNo
            AND s.season_id = (SELECT season_id FROM matches WHERE match_id = :matchNo)
            ORDER BY t.team_short_name, p.role, p.player_name
            """, nativeQuery = true)
    List<Object[]> findByMatchMatchId(@Param("matchNo") Integer matchNo);

    @Modifying
    @Query(value = """
          INSERT INTO dream_player_team (match_no, player_id, playing_11, playing_15, is_captain,\s
                       is_vice_captain, dream_team, selection_percentage)
                   VALUES (:matchNo, :playerId, :isPlaying11, :isPlaying15, :isCaptain, :isViceCaptain,\s
                       :isDreamTeam, :selectionPercentage)
                   ON DUPLICATE KEY UPDATE
                       playing_11 = CASE\s
                           WHEN :isPlaying11 IS NOT NULL THEN :isPlaying11\s
                           ELSE playing_11\s
                       END,
                       playing_15 = CASE\s
                           WHEN :isPlaying15 IS NOT NULL THEN :isPlaying15\s
                           ELSE playing_15\s
                       END,
                       is_captain = CASE\s
                           WHEN :isCaptain IS NOT NULL THEN :isCaptain\s
                           ELSE is_captain\s
                       END,
                       is_vice_captain = CASE\s
                           WHEN :isViceCaptain IS NOT NULL THEN :isViceCaptain\s
                           ELSE is_vice_captain\s
                       END,
                       dream_team = CASE\s
                           WHEN :isDreamTeam IS NOT NULL THEN :isDreamTeam\s
                           ELSE dream_team\s
                       END,
                       selection_percentage = CASE\s
                           WHEN :selectionPercentage IS NOT NULL THEN :selectionPercentage\s
                           ELSE selection_percentage\s
                       END
        """, nativeQuery = true)
    void upsertPlayerRoles(
        @Param("matchNo") Integer matchNo,
        @Param("playerId") Integer playerId,
        @Param("isPlaying11") Boolean isPlaying11,
        @Param("isPlaying15") Boolean isPlaying15,
        @Param("isCaptain") Boolean isCaptain,
        @Param("isViceCaptain") Boolean isViceCaptain,
        @Param("isDreamTeam") Boolean isDreamTeam,
        @Param("selectionPercentage") Double selectionPercentage
    );
}
