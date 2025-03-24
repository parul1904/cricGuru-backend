package in.cricguru.repository;

import in.cricguru.entity.DreamPlayerTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DreamPlayerTeamRepository extends JpaRepository<DreamPlayerTeam, Long> {
    @Query("SELECT dpt FROM DreamPlayerTeam dpt JOIN dpt.match m WHERE m.matchNo = :matchNo")
    List<DreamPlayerTeam> findByMatchMatchId(@Param("matchNo") Integer matchNo);
}
