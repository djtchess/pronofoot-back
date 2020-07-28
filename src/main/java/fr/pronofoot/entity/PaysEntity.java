package fr.pronofoot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Setter
@Getter
@Entity
@Table(name = "tpays")
public class PaysEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pays_seq")
    @SequenceGenerator(name="pays_seq", sequenceName="PAYS_SEQ", allocationSize=0)
    @Column(name = "id")
    private Long id;
 
    @Column(name = "nom")
    private String nomPays;

    @OneToMany(mappedBy="pays", fetch = FetchType.EAGER)
    private Collection<CompetitionEntity> competitions;



 
   
}