package fr.pronofoot.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompetitionModel {

    private Long id;
    private String nom;
    private String code;
    private List<TeamModel> teamsList;

}