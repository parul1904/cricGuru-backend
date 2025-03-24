package in.cricguru.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DreamPlayerTeamId implements Serializable {
    
    @Column(name = "match_no")
    private Integer matchNo;
    
    @Column(name = "player_id")
    private Integer playerId;
}