package fr.pronofoot.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SaisonModel {

    private Long id;
    private Date dateDebut;
    private Date dateFin;
    private Long currentMatchDay;


}