package fr.pronofoot.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MatchListModel {

    private Long count;
    private CompetitionModel competition;
    private List<MatchModel> matchList;

}