package fr.pronofoot.controller;

import fr.pronofoot.jfdata.manager.JfdataManager;
import fr.pronofoot.jfdata.model.standing.Standing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
@RestController
@RequestMapping(value="/pronofoot")
public class StandingController {

    @Autowired
    private JfdataManager jfdataManager;
 
    @RequestMapping(value="/standing", method= RequestMethod.GET, produces= "application/json")
    public Standing getCompetitions(){

        Standing actual = jfdataManager.getStanding(2015);
        return actual;



    }
 
    
 
}