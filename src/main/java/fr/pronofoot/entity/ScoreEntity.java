package fr.pronofoot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Setter
@Getter
@Entity
@Table(name = "tscore")
public class ScoreEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="score_seq")
    @SequenceGenerator(name="score_seq", sequenceName="SCORE_SEQ", allocationSize=0)
    @Column(name = "id")
    private Long id;

    @Column(name = "utc_date")
    private Date utcDate;

    @Column(name = "match_day")
    private int matchDay;

    @Column(name = "date_maj")
    private Date dateMaj;

    @Column(name = "but_home_full")
    private int butHomeFull;

    @Column(name = "but_away_full")
    private int butAwayFull;

    @Column(name = "but_home_half")
    private int butHomeHalf;

    @Column(name = "but_away_half")
    private int butAwayHalf;

    @Column(name = "but_home_extra")
    private int butHomeExtra;

    @Column(name = "but_away_extra")
    private int butAwayExtra;

    @Column(name = "but_home_penalties")
    private int butHomePenalties;

    @Column(name = "but_away_penalties")
    private int butAwayPenalties;


    @Column(name = "api_id")
    private Long apiId;

//    @ManyToOne
//    private PaysEntity pays;
 
   
}