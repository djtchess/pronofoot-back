package fr.pronofoot.entity;

import javax.persistence.*;

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
 
    
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }

	public String getNomPays() {
		return nomPays;
	}

	public void setNomPays(String nomPays) {
		this.nomPays = nomPays;
	}


 
   
}