package in.cricguru.repository;

import in.cricguru.entity.DreamPlayerTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DreamPlayerTeamRepository extends JpaRepository<DreamPlayerTeam, Long> {
    
    @Query(value = """
            SELECT p.player_id, p.player_name, p.role, p.player_img_url
            FROM dream_player_team dpt
            LEFT JOIN players p ON dpt.player_id = p.player_id
            WHERE dpt.match_no = :matchNo and dream_team=1;
            """, nativeQuery = true)
    List<Object[]> findActualDreamTeamByMatchNo(@Param("matchNo") Integer matchNo);

    @Query(value = "SELECT DISTINCT match_no FROM dream_player_team WHERE dream_team = true", nativeQuery = true)
    List<Integer> findMatchesWithDreamTeam();
    
    @Query("SELECT dpt FROM DreamPlayerTeam dpt WHERE dpt.match.matchId = :matchNo")
    List<DreamPlayerTeam> findByMatchMatchId(@Param("matchNo") Integer matchNo);
}
