package fr.pronofoot.metier;

import fr.pronofoot.jfdata.manager.JfdataManager;
import fr.pronofoot.jfdata.model.team.TeamList;
import fr.pronofoot.model.TeamModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamMetier {

    @Autowired
    private JfdataManager jfdataManager;


    public List<TeamModel> retrieveTeams(){
        List<TeamModel> listePays = new ArrayList<>();
        TeamList teamList = jfdataManager.getTeamsByCompetition(2015);

        teamList.getTeams().forEach(team -> System.out.println(team.getName()));
        return listePays;
    }


}
