package fr.pronofoot.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tteam")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="team_seq")
    @SequenceGenerator(name="team_seq", sequenceName="TEAM_SEQ", allocationSize=0)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "shortname")
    private String shortName;

    @Column(name = "tla")
    private String tla;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "website")
    private String website;

    @Column(name = "founded")
    private String founded;

    @Column(name = "club_colors")
    private String clubColors;

    @Column(name = "venue")
    private String venue;

    @Column(name = "last_updated")
    private Date lastUpdated;

    @Column(name = "api_id")
    private Long apiId;

    @ManyToOne
    private PaysEntity pays;

    @OneToMany(mappedBy = "winner")
    private List<SaisonEntity> saisonEntityList;

    @OneToMany(mappedBy = "teamEntity", fetch=FetchType.LAZY, cascade={CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE})
    private List<CompetitionTeamEntity> competitionTeamEntityList = new ArrayList<>();

    public void addCompetition(CompetitionEntity c) {
        CompetitionTeamEntity competitionTeamEntity = new CompetitionTeamEntity();
        competitionTeamEntity.setTeamEntity(this);
        competitionTeamEntity.setCompetitionEntity(c);
        competitionTeamEntity.setSaisonEntity(c.getCurrentSaison());
        this.competitionTeamEntityList.add(competitionTeamEntity);
    }

    @OneToMany(mappedBy = "homeTeam", fetch=FetchType.LAZY)
    private List<MatchEntity> matchHomeList = new ArrayList<>();

    @OneToMany(mappedBy = "awayTeam", fetch=FetchType.LAZY)
    private List<MatchEntity> matchAwayList = new ArrayList<>();
   
}