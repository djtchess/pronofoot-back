package fr.pronofoot.controller;

import fr.pronofoot.entity.CompetitionEntity;
import fr.pronofoot.entity.PaysEntity;
import fr.pronofoot.metier.CompetitionMetier;
import fr.pronofoot.model.CompetitionModel;
import fr.pronofoot.model.PaysModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
@RestController
@RequestMapping(value="/pronofoot")
public class UpdateBDDController {

    @Autowired
    private CompetitionMetier cm;
 
    @RequestMapping(value="/retrieveAllCompetitions", method= RequestMethod.GET, produces= "application/json")
    public List<CompetitionModel> retrieveAllCompetitions(){
        List<CompetitionEntity> competitionEntities = cm.retrieveCompetitions();
        cm.saveAllCompetitions(competitionEntities);
        List<CompetitionModel> competitions = new ArrayList<>();
        competitionEntities.forEach(competitionEntity -> competitions.add(convertToCompetitionModel(competitionEntity)));
        return competitions;
    }


    @RequestMapping(value="/retrieveAllpays", method= RequestMethod.GET, produces= "application/json")
    public List<PaysModel> retrieveAllpays(){
        List<PaysEntity> pays =  cm.retrievePays();
        cm.saveAllPays(pays);
        List<PaysModel> paysModels = new ArrayList<>();
        pays.forEach(paysEntity -> paysModels.add(convertToPaysPModel(paysEntity)));
        return paysModels;
    }

    private PaysModel convertToPaysPModel( PaysEntity entity){
        PaysModel model = new PaysModel();
        model.setId(entity.getId());
        Collection<CompetitionModel> competitions = new ArrayList<>();
        entity.getCompetitions().forEach(competitionEntity -> competitions.add(convertToCompetitionModel(competitionEntity)));
        model.setCompetitions(competitions);
        model.setNomPays(entity.getNomPays());
        return model;
    }

    private CompetitionModel convertToCompetitionModel(CompetitionEntity entity){
        CompetitionModel model = new CompetitionModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setNom(entity.getNom());
        return model;
    }

}