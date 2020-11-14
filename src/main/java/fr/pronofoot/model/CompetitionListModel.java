package fr.pronofoot.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompetitionListModel {

    private Long count;
    private List<CompetitionModel> competitionsList;

}