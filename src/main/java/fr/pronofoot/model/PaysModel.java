package fr.pronofoot.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Setter
@Getter
public class PaysModel {

    private Long id;
    private String nomPays;
    private Collection<CompetitionModel> competitions;

}