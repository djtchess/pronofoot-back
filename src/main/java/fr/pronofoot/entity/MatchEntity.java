package fr.pronofoot.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tmatch")
public class MatchEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="match_seq")
    @SequenceGenerator(name="match_seq", sequenceName="MATCH_SEQ", allocationSize=0)
    @Column(name = "id")
    private Long id;

    @Column(name = "utc_date")
    private Date utcDate;

    @Column(name = "match_day")
    private int matchDay;

    @Column(name = "date_maj")
    private Date dateMaj;

    @Column(name = "status")
    private String status;

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

    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "team_home_id" )
    private TeamEntity homeTeam;

    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "team_away_id")
    private TeamEntity awayTeam;

    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "saison_id" )
    private SaisonEntity saisonEntity;

    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "competition_id" )
    private CompetitionEntity competitionEntity;
 
   
}