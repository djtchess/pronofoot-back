package fr.pronofoot.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "api_id")
    private Long apiId;

    @OneToMany(mappedBy="pays", fetch = FetchType.LAZY)
    private Collection<CompetitionEntity> competitions;


    public boolean isBigPays(){
        System.out.println(this.nomPays.toUpperCase().trim());
        if (this.nomPays.toUpperCase().trim().equals("FRANCE") || this.nomPays.toUpperCase().trim().equals("SPAIN")) {
            return true;
        }
        return false;
    }
 
   
}