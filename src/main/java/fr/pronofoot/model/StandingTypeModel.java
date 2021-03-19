package fr.pronofoot.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StandingTypeModel {

    private String stage;
    private String type;
    private String group;
    private List<StandingTeamModel> standing = new ArrayList<>();

    public void addStandingTeamModel(StandingTeamModel standingTeamModel){
        this.standing.add(standingTeamModel);
    }

}