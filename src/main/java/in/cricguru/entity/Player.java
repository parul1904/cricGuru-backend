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
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Integer playerId;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "player_img_url")
    private String playerImgUrl;
    private String role;
    @Column(name = "batting_style")
    private String battingStyle;
    @Column(name = "bowling_style")
    private String bowlingStyle;
    private String country;
}