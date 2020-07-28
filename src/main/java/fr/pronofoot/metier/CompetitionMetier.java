package fr.pronofoot.metier;

import fr.pronofoot.dao.CompetitionDao;
import fr.pronofoot.dao.PaysDao;
import fr.pronofoot.entity.CompetitionEntity;
import fr.pronofoot.entity.PaysEntity;
import fr.pronofoot.jfdata.manager.JfdataManager;
import fr.pronofoot.jfdata.model.competition.Competition;
import fr.pronofoot.jfdata.model.competition.CompetitionList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompetitionMetier {

    @Autowired
    private JfdataManager jfdataManager;

    @Autowired
    private PaysDao paysDao;

    @Autowired
    private CompetitionDao competitionDao;

    public void saveAllPays(List<PaysEntity> pays){
        this.paysDao.saveAllPays(pays);
    }

    public void saveAllCompetitions(List<CompetitionEntity> competitions){
        this.competitionDao.saveAllCompetitions(competitions);
    }

    public List<PaysEntity> getAllPays(){
        return this.paysDao.listPays();
    }
    public PaysEntity getPays(Long id){
        return this.paysDao.getPaysById(id);
    }

    public List<PaysEntity> retrievePays(){
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

    public List<CompetitionEntity> retrieveCompetitions(){
        List<CompetitionEntity> listeCompetitions = new ArrayList<>();
        CompetitionList competitionList = jfdataManager.getAllCompetitions();

        competitionList.getCompetitions().forEach(competition -> {
            PaysEntity pays = paysDao.getPaysByName(competition.getArea().getName());
            CompetitionEntity competitionEntity = convertCompetitionToCompetitionEntity(competition);
            competitionEntity.setPays(pays);
            listeCompetitions.add(competitionEntity);
        });
        return listeCompetitions;
    }

    private PaysEntity convertAreaToPaysEntity(String area){
        PaysEntity paysEntity = new PaysEntity();
        paysEntity.setNomPays(area);
        return  paysEntity;
    }

    private CompetitionEntity convertCompetitionToCompetitionEntity(Competition competition){
        CompetitionEntity competitionEntity = new CompetitionEntity();
        competitionEntity.setNom(competition.getName());
        competitionEntity.setCode(competition.getCode());
        return  competitionEntity;
    }
}
