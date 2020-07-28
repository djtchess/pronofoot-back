package fr.pronofoot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    @ManyToOne
    private PaysEntity pays;
 
   
}