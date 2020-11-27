package fr.pronofoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.pronofoot.metier.TeamMetier;
import fr.pronofoot.model.TeamModel;

@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
@RestController
@RequestMapping(value="/pronofoot")
public class TeamController {

    @Autowired
    private TeamMetier teamMetier;
 

    @GetMapping("/team/{id}")
    public TeamModel getOneTeam(@PathVariable Long id) {
        System.out.println("getOneTeam appelée "+ id);
        TeamModel team = teamMetier.getTeamById(id);
        return team;
    }
 
    
 
}