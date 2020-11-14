package fr.pronofoot.entity;

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
@Table(name = "tcompetition_team")
public class CompetitionTeamEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="competition_team_seq")
    @SequenceGenerator(name="competition_team_seq", sequenceName="COMPETITION_TEAM_SEQ", allocationSize=0)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private  CompetitionEntity competitionEntity;

    public void setCompetitionEntity(CompetitionEntity competitionEntity) {
        this.competitionEntity = competitionEntity;
    }

    public void setTeamEntity(TeamEntity teamEntity) {
        this.teamEntity = teamEntity;
    }

    public void setSaisonEntity(SaisonEntity saisonEntity) {
        this.saisonEntity = saisonEntity;
    }

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamEntity teamEntity;

    @ManyToOne
    @JoinColumn(name = "saison_id")
    private SaisonEntity saisonEntity;



}