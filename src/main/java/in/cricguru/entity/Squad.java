package in.cricguru.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "squads")
public class Squad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "squad_id")
    private Integer squadId;

    @ManyToOne
    @JoinColumn(name = "season_id",  referencedColumnName = "season_id")
    private Season season;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    private Player player;

    @Column(name = "playing_11")
    private Boolean playing11;

}
