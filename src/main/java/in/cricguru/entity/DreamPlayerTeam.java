package in.cricguru.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dream_player_team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DreamPlayerTeam {

    @EmbeddedId
    private DreamPlayerTeamId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("matchNo")
    @JoinColumn(name = "match_no")
    private Match match;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("playerId")
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "playing_11", columnDefinition = "TINYINT(1)")
    private Boolean playing11;

    @Column(name = "playing_15", columnDefinition = "TINYINT(1)")
    private Boolean playing15;

    @Column(name = "is_captain", columnDefinition = "TINYINT(1)")
    private Boolean isCaptain;

    @Column(name = "is_vice_captain", columnDefinition = "TINYINT(1)")
    private Boolean isViceCaptain;

    @Column(name = "dream_team", columnDefinition = "TINYINT(1)")
    private Boolean dreamTeam;

    @Column(name = "selection_percentage", columnDefinition = "DOUBLE")
    private Double selectionPercentage;
}