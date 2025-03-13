package in.cricguru.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "match_stats")
public class Stats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_stats_id")
    private Integer matchStatsId;

    @ManyToOne
    @JoinColumn(name = "season_id",  referencedColumnName = "season_id")
    private Season season;

    @ManyToOne
    @JoinColumn(name = "match_no", referencedColumnName = "match_id", nullable = false)
    private Match matchNo;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    private Player playerId;

    @Column(name = "runs_scored")
    private Integer runsScored;

    @Column(name= "ball_faced")
    private Integer ballFaced;

    private Integer fours;

    private Integer sixes;

    @Column(name = "strike_rate")
    private Double strikeRate;

    @Column(name = "is_dismissed")
    private Boolean isDismissed;

    @Column(name = "inning_played")
    private Boolean inningPlayed;

    private Double overs;

    @Column(name = "total_wickets")
    private Integer totalWickets;

    @Column(name = "bowled_lbw")
    private Integer bowledLbw;

    @Column(name = "other_dismissal")
    private Integer otherDismissal;

    @Column(name = "runs_conceded")
    private Integer runsConceded;

    private Integer dots;

    private Integer maiden;

    @Column(name = "economy_rate")
    private Double economyRate;

    @Column(name = "catch_taken")
    private Integer catchTaken;

    private Integer stumping;
    @Column(name = "direct_runout")
    private Integer directRunout;
    @Column(name = "in_direct_runout")
    private Integer inDirectRunout;
    @Column(name = "is_impact_player")
    private Boolean isImpactPlayer;

    @Column(name = "total_point_dream11_new_system")
    private Integer totalPointDream11NewSystem;

    @Column(name = "total_point_dream11_old_system")
    private Integer totalPointDream11OldSystem;

    @Column(name = "total_point_my11_circle_system")
    private Integer totalPointMy11CircleSystem;

} 