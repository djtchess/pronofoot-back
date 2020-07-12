package fr.pronofoot.controller;

import fr.pronofoot.jfdata.manager.JfdataManager;
import fr.pronofoot.jfdata.model.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
@RestController
@RequestMapping(value="/pronofoot")
public class TeamController {

    @Autowired
    private JfdataManager jfdataManager;
 
    @RequestMapping(value="/team", method= RequestMethod.GET, produces= "application/json")
    public Team getCompetitions(){

        Team actual = jfdataManager.getTeam(18);
        return actual;
    }

    @GetMapping("/team/{id}")
    public Team getOneTeam(@PathVariable Long id) {
        Team actual = jfdataManager.getTeam(id.intValue());
        return actual;
    }
 
    
 
}