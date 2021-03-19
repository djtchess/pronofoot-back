package fr.pronofoot.model;

import java.util.List;

import fr.pronofoot.jfdata.model.competition.Competition;
import fr.pronofoot.jfdata.model.competition.CompetitionSeason;
import fr.pronofoot.jfdata.model.standing.StandingType;
import fr.pronofoot.jfdata.model.team.Team;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StandingModel {

    private Competition competition;
    private CompetitionSeason season;
    private List<StandingTypeModel> standings;

}