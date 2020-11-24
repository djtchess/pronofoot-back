package fr.pronofoot.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.pronofoot.entity.CompetitionEntity;
import fr.pronofoot.entity.PaysEntity;
import fr.pronofoot.metier.CompetitionMetier;
import fr.pronofoot.metier.MatchMetier;
import fr.pronofoot.model.CompetitionListModel;
import fr.pronofoot.model.CompetitionModel;
import fr.pronofoot.model.MatchListModel;
import fr.pronofoot.model.PaysListModel;
import fr.pronofoot.model.PaysModel;

@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
@RestController
@RequestMapping(value="/pronofoot")
public class CompetitionController {

    @Autowired
    private CompetitionMetier cm;

    @Autowired
    private MatchMetier mm;

    @RequestMapping(value="/pays", method= RequestMethod.GET, produces= "application/json")
    public PaysListModel retrieveAllpays(){
        PaysListModel paysListModel = new PaysListModel();
        List<PaysModel> paysModels = new ArrayList<>();
        cm.getAllPays().stream().filter(PaysEntity::isBigPays).collect(Collectors.toList()).forEach(paysEntity -> {
            PaysModel model = convertToPaysPModel(paysEntity);
            model.setCompetitions(null);
            paysModels.add(model);
        });
        paysListModel.setPaysList(paysModels);
        paysListModel.setCount(Long.valueOf(paysModels.size()));
        return paysListModel;
    }

    @GetMapping("/pays/{id}")
    public PaysModel getOnePays(@PathVariable Long id) {
        PaysModel paysModel = convertToPaysPModel(cm.getPays(id));
        paysModel.setCompetitions(null);
        return paysModel;
    }
    @GetMapping("/pays/{id}/competitions")
    public CompetitionListModel getOnePaysAllCompetitions(@PathVariable Long id) {
        System.out.println("/pays/"+id+"/competitionsappelée");
        PaysModel paysModel = convertToPaysPModel(cm.getPays(id));
        CompetitionListModel competitionListModel = new CompetitionListModel();
        competitionListModel.setCompetitionsList(paysModel.getCompetitions().stream().collect(Collectors.toList()));
        competitionListModel.setCount(Long.valueOf(competitionListModel.getCompetitionsList().size()));
        return competitionListModel;
    }

    @GetMapping("/competitions/{id}")
    public CompetitionModel getCompetitionById(@PathVariable Long id) {
        System.out.println("/competitions/"+id+" appelée");
        return cm.getCompetitionById(id);
    }

    @GetMapping("/competitions/{id}/matchs")
    public MatchListModel getAllMatchByCompetitionId(@PathVariable Long id) {
        MatchListModel matchListModel = new MatchListModel();
        System.out.println("/competitions/"+id+"/matchs appelée");
        matchListModel.setMatchList(mm.getAllMatchByCompetition(id));
        matchListModel.setCount(Long.valueOf(mm.getAllMatchByCompetition(id).size()));
        matchListModel.setCompetition(cm.getCompetitionById(id));
        return matchListModel;
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