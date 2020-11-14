package fr.pronofoot.entity;

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
@Table(name = "tcompetition")
public class CompetitionEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="competition_seq")
    @SequenceGenerator(name="competition_seq", sequenceName="COMPETITION_SEQ", allocationSize=0)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "code")
    private String code;

    @Column(name = "api_id")
    private Long apiId;

    @ManyToOne
    private PaysEntity pays;

    @OneToMany(mappedBy = "competitionEntity")
    private List<CompetitionTeamEntity> competitionTeamEntityList;


    @OneToMany(mappedBy = "competitionEntity", fetch=FetchType.LAZY, cascade={CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE})
    private List<SaisonEntity> saisonEntityList = new ArrayList<>();

    public void addSaison(SaisonEntity saison){
        this.saisonEntityList.add(saison);
        saison.setCompetitionEntity(this);
    }

}