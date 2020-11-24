package fr.pronofoot.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tsaison")
public class SaisonEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="saison_seq")
    @SequenceGenerator(name="saison_seq", sequenceName="SAISON_SEQ", allocationSize=0)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_debut")
    private Date dateDebut;

    @Column(name = "date_fin")
    private Date dateFin;

    @Column(name = "current_match_day")
    private Long currentMatchDay;

    @ManyToOne
    @JoinColumn(name = "winner")
    private TeamEntity winner;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private CompetitionEntity competitionEntity;

    @OneToMany(mappedBy = "saisonEntity")
    private List<CompetitionTeamEntity> competitionTeamEntityList = new ArrayList<>();

    public void setCompetitionEntity(CompetitionEntity competitionEntity) {
        this.competitionEntity = competitionEntity;
    }

    @OneToMany(mappedBy = "saisonEntity")
    private List<MatchEntity> matchEntityList = new ArrayList<>();
}