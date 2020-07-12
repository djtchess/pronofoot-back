package fr.pronofoot.metier;

import fr.pronofoot.dao.PaysDao;
import fr.pronofoot.entity.PaysEntity;
import fr.pronofoot.jfdata.manager.JfdataManager;
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

    public void saveAllPays(List<PaysEntity> pays){
        this.paysDao.saveAllPays(pays);
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

    private PaysEntity convertAreaToPaysEntity(String area){
        PaysEntity paysEntity = new PaysEntity();
        paysEntity.setNomPays(area);
        return  paysEntity;
    }
}
