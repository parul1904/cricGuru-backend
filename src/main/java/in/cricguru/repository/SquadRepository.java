package in.cricguru.repository;

import in.cricguru.dto.DreamPlayerTeamDto;
import in.cricguru.dto.PlayerRoleUpdate;
import in.cricguru.entity.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface SquadRepository extends JpaRepository<Squad, Long> {

    @Query(value = """
    SELECT p.player_id, p.player_name, s.team_id, s.playing_11, s.is_captain, s.is_vice_captain, s.playing_15
    FROM cricguru_db.squads s
    LEFT JOIN players p ON p.player_id = s.player_id
    WHERE s.season_id = 2 AND s.team_Id = :teamId;
    """, nativeQuery = true)
    List<Object[]> findSquadDetailsByTeam(Integer teamId);

    @Modifying
    @Query(value = """
    UPDATE cricguru_db.squads 
    SET 
        playing_11 = CASE 
            WHEN :isPlaying11 IS NOT NULL THEN :isPlaying11 
            ELSE playing_11 
        END,
        is_captain = CASE 
            WHEN :isCaptain IS NOT NULL THEN :isCaptain 
            ELSE is_captain 
        END,
        is_vice_captain = CASE 
            WHEN :isViceCaptain IS NOT NULL THEN :isViceCaptain 
            ELSE is_vice_captain 
        END,
        playing_15 = CASE 
            WHEN :isPlaying15 IS NOT NULL THEN :isPlaying15 
            ELSE playing_15 
        END
    WHERE player_id = :playerId 
    AND season_id = :seasonId
    """, nativeQuery = true)
    void updatePlayerRole(
        @Param("playerId") Long playerId,
        @Param("seasonId") Long seasonId,
        @Param("isPlaying11") Boolean isPlaying11,
        @Param("isCaptain") Boolean isCaptain,
        @Param("isViceCaptain") Boolean isViceCaptain,
        @Param("isPlaying15") Boolean isPlaying15
    );

    @Query(value = """
            SELECT p.player_id, p.player_name, p.role, t.team_id, t.team_short_name, d.playing_11, d.playing_15, d.is_captain,
                d.is_vice_captain, d.dream_team, d.selection_percentage
                FROM dream_player_team d LEFT JOIN players p ON p.player_id = d.player_id
                LEFT JOIN cricguru_db.squads s ON s.player_id = p.player_id LEFT JOIN cricguru_db.teams t ON t.team_id = s.team_id
                WHERE s.season_id = 2 AND s.team_id IN (:team1Id, :team2Id)
    """, nativeQuery = true)
    List<Object[]> findSquadPlayersByTeams(@Param("team1Id") Long team1Id, @Param("team2Id") Long team2Id);
}
