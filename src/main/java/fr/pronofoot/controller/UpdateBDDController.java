package fr.pronofoot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.pronofoot.entity.MatchEntity;
import fr.pronofoot.metier.CompetitionMetier;
import fr.pronofoot.metier.MatchMetier;
import fr.pronofoot.metier.TeamMetier;
import fr.pronofoot.model.CompetitionModel;
import fr.pronofoot.model.PaysModel;
import fr.pronofoot.model.TeamModel;

@CrossOrigin(origins = {"http://localhost:4200","https://sitepersodavid.pagesperso-orange.fr2"}, allowCredentials = "true")
@RestController
@RequestMapping(value="/pronofoot")
public class UpdateBDDController {

    @Autowired
    private CompetitionMetier cm;

    @Autowired
    private TeamMetier tm;

    @Autowired
    private MatchMetier mm;

    @RequestMapping(value="/createMatchByCompetition/{id}", method= RequestMethod.POST, produces= "application/json")
    public List<MatchEntity> retrieveMatchByCompetition(@PathVariable Long id){
        mm.retrieveAllMatchByCompetition(id);
        return new ArrayList<>();
    }

    @RequestMapping(value="/createMatchByCompetition/{id}/{day}", method= RequestMethod.POST, produces= "application/json")
    public List<MatchEntity> retrieveMatchByCompetitionAndDay(@PathVariable Long id, @PathVariable String day){
        mm.retrieveAllMatchByCompetitionByDay(id, day);
        return new ArrayList<>();
    }

    @RequestMapping(value="/createAllTeams", method= RequestMethod.POST, produces= "application/json")
    public List<TeamModel> retrieveAllTeams(){
        List<TeamModel> listTeams = tm.createAllTeamsByCompetition(2015L);
        return listTeams;
    }


    @RequestMapping(value="/createAllCompetitions", method= RequestMethod.POST, produces= "application/json")
    public List<CompetitionModel> retrieveAllCompetitions(){
           return cm.createAllCompetitions();
    }

    @RequestMapping(value="/createAllPays", method= RequestMethod.POST, produces= "application/json")
    public List<PaysModel> retrieveAllpays(){
        return cm.createAllpays();
    }



}