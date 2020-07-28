package fr.pronofoot.controller;

import fr.pronofoot.entity.CompetitionEntity;
import fr.pronofoot.entity.PaysEntity;
import fr.pronofoot.metier.CompetitionMetier;
import fr.pronofoot.model.CompetitionModel;
import fr.pronofoot.model.PaysModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
@RestController
@RequestMapping(value="/pronofoot")
public class CompetitionController {

    @Autowired
    private CompetitionMetier cm;

    @RequestMapping(value="/pays", method= RequestMethod.GET, produces= "application/json")
    public List<PaysModel> retrieveAllpays(){
        List<PaysModel> paysModels = new ArrayList<>();
        cm.getAllPays().forEach(paysEntity -> {
            PaysModel model = convertToPaysPModel(paysEntity);
            model.setCompetitions(null);
            paysModels.add(model);
        });
        return paysModels;
    }

    @GetMapping("/pays/{id}")
    public PaysModel getOnePays(@PathVariable Long id) {
        PaysModel paysModel = convertToPaysPModel(cm.getPays(id));
        paysModel.setCompetitions(null);
        return paysModel;
    }
    @GetMapping("/pays/{id}/competitions")
    public PaysModel getOnePaysAllCompetitions(@PathVariable Long id) {
        PaysModel paysModel = convertToPaysPModel(cm.getPays(id));
        return paysModel;
    }

    private PaysModel convertToPaysPModel( PaysEntity entity){
        PaysModel model = new PaysModel();
        model.setId(entity.getId());
        Collection<CompetitionModel> competitions = new ArrayList<>();
        if (entity.getCompetitions() != null && !entity.getCompetitions().isEmpty()){
            entity.getCompetitions().forEach(competitionEntity -> competitions.add(convertToCompetitionModel(competitionEntity)));
        }

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