package fr.pronofoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.pronofoot.metier.StandingMetier;
import fr.pronofoot.model.StandingModel;

@CrossOrigin(origins = {"http://localhost:4200","https://sitepersodavid.pagesperso-orange.fr"}, allowCredentials = "true")
@RestController
@RequestMapping(value="/pronofoot")
public class StandingController {

    @Autowired
    private StandingMetier standingMetier;
 
    @RequestMapping(value="/competitions/{id}/standings", method= RequestMethod.GET, produces= "application/json")
    public StandingModel getStandingsByCompetion(@PathVariable Long id){
        standingMetier.getClassement(id);
        return null;



    }
 
    
 
}