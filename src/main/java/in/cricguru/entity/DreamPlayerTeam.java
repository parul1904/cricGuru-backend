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

    @Column(name = "dream_team", columnDefinition = "TINYINT(1)")
    private Boolean dreamTeam;

    @Column(name = "last_match", columnDefinition = "TINYINT(1)")
    private Boolean lastMatch;

    @Column(name = "last_3_match", columnDefinition = "TINYINT(1)")
    private Boolean last3Match;

    @Column(name = "last_5_match", columnDefinition = "TINYINT(1)")
    private Boolean last5Match;

    @Column(name = "is_captain", columnDefinition = "TINYINT(1)")
    private Boolean isCaptain;

    @Column(name = "is_vice_captain", columnDefinition = "TINYINT(1)")
    private Boolean isViceCaptain;

    @Column(name = "playing_15", columnDefinition = "TINYINT(1)")
    private Boolean playing15;
}