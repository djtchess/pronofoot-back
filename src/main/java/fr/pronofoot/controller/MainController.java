package fr.pronofoot.controller;

import fr.pronofoot.entity.PaysEntity;
import fr.pronofoot.metier.CompetitionMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
@RestController
@RequestMapping(value="/pronofoot")
public class MainController {

    @Autowired
    private CompetitionMetier cm;
 
    @RequestMapping(value="/pays", method= RequestMethod.GET, produces= "application/json")
    public List<PaysEntity> getCompetitions(){
        List<PaysEntity> pays =  cm.retrievePays();
        cm.saveAllPays(pays);
        return pays;
    }


 
    
 
}