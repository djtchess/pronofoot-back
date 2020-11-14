package fr.pronofoot.metier;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.pronofoot.dao.CompetitionDao;
import fr.pronofoot.dao.PaysDao;
import fr.pronofoot.entity.CompetitionEntity;
import fr.pronofoot.entity.PaysEntity;
import fr.pronofoot.entity.SaisonEntity;
import fr.pronofoot.jfdata.manager.JfdataManager;
import fr.pronofoot.jfdata.model.competition.Competition;
import fr.pronofoot.jfdata.model.competition.CompetitionList;
import fr.pronofoot.model.CompetitionModel;
import fr.pronofoot.model.PaysModel;

@Service
public class CompetitionMetier extends BaseMetier {

    @Autowired
    private JfdataManager jfdataManager;

    @Autowired
    private PaysDao paysDao;

    @Autowired
    private CompetitionDao competitionDao;

    private void saveAllPays(List<PaysEntity> pays) {
        this.paysDao.saveAllPays(pays);
    }

    private void saveAllCompetitions(List<CompetitionEntity> competitions) {
        this.competitionDao.saveAllCompetitions(competitions);
    }

    public List<PaysEntity> getAllPays() {
        return this.paysDao.listPays();
    }

    public CompetitionModel getCompetitionsByPaysId(Long id){
        return convertToCompetitionModel(competitionDao.getCompetitionById(id));
    }

    public PaysEntity getPays(Long id) {
        return this.paysDao.getPaysById(id);
    }

    public List<PaysEntity> retrievePays() {
        List<PaysEntity> listePays = new ArrayList<>();
        CompetitionList competitionList = jfdataManager.getAllCompetitions();

        competitionList.getCompetitions().stream()
                .map(competition -> competition.getArea().getName())
                .distinct()
                .sorted()
                .collect(Collectors.toList())
                .forEach(area -> listePays.add(convertAreaToPaysEntity(area)));
        return listePays;
    }

    public List<CompetitionEntity> retrieveCompetitions() {
        List<CompetitionEntity> listeCompetitions = new ArrayList<>();
        CompetitionList competitionList = jfdataManager.getAllCompetitions();

        competitionList.getCompetitions().forEach(competition -> {
            PaysEntity pays = paysDao.getPaysByName(competition.getArea().getName());
            CompetitionEntity competitionEntity = convertCompetitionToCompetitionEntity(competition);
            competitionEntity.setPays(pays);
            SaisonEntity saisonEntity = getSaisonEntity(competition);
            if (saisonEntity !=null ){
                competitionEntity.addSaison(getSaisonEntity(competition));
            }
            listeCompetitions.add(competitionEntity);


        });
        return listeCompetitions;
    }

    private SaisonEntity getSaisonEntity(Competition competition) {
        SaisonEntity saisonEntity = new SaisonEntity();
        if (competition.getCurrentSeason() == null) {
            return null;
        }
        if (competition.getCurrentSeason().getCurrentMatchday() != null) {
            saisonEntity.setCurrentMatchDay(Long.valueOf(competition.getCurrentSeason().getCurrentMatchday()));
        } else {
            saisonEntity.setCurrentMatchDay(1L);
        }
        saisonEntity.setDateDebut(Date.valueOf(competition.getCurrentSeason().getStartDate()));
        saisonEntity.setDateFin(Date.valueOf(competition.getCurrentSeason().getEndDate()));
        saisonEntity.setWinner(null);
        return saisonEntity;
    }

    public List<CompetitionModel> createAllCompetitions() {
        List<CompetitionEntity> competitionEntities = retrieveCompetitions();
        saveAllCompetitions(competitionEntities);
        List<CompetitionModel> competitions = new ArrayList<>();
        competitionEntities.forEach(competitionEntity -> competitions.add(convertToCompetitionModel(competitionEntity)));
        return competitions;
    }

    public List<PaysModel> createAllpays() {
        List<PaysEntity> pays = retrievePays();
        saveAllPays(pays);
        List<PaysModel> paysModels = new ArrayList<>();
        pays.forEach(paysEntity -> paysModels.add(convertToPaysPModel(paysEntity)));
        return paysModels;
    }

    private PaysEntity convertAreaToPaysEntity(String area) {
        PaysEntity paysEntity = new PaysEntity();
        paysEntity.setNomPays(area);
        return paysEntity;
    }

    private CompetitionEntity convertCompetitionToCompetitionEntity(Competition competition) {
        CompetitionEntity competitionEntity = new CompetitionEntity();
        competitionEntity.setNom(competition.getName());
        competitionEntity.setCode(competition.getCode());
        competitionEntity.setApiId(Long.valueOf(competition.getId()));
        return competitionEntity;
    }


    private PaysModel convertToPaysPModel(PaysEntity entity) {
        PaysModel model = map(entity, PaysModel.class);
        Collection<CompetitionModel> competitions = new ArrayList<>();
        if (entity.getCompetitions() != null) {
            entity.getCompetitions().forEach(competitionEntity -> competitions.add(convertToCompetitionModel(competitionEntity)));
        }
        model.setCompetitions(competitions);
        return model;
    }

    private CompetitionModel convertToCompetitionModel(CompetitionEntity entity) {
        return map(entity, CompetitionModel.class);
    }
}
