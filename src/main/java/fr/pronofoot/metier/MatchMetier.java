package fr.pronofoot.metier;

import fr.pronofoot.dao.CompetitionDao;
import fr.pronofoot.entity.CompetitionEntity;
import fr.pronofoot.jfdata.manager.JfdataManager;
import fr.pronofoot.jfdata.model.competition.CompetitionList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchMetier {

    @Autowired
    private JfdataManager jfdataManager;

    @Autowired
    private CompetitionDao competitionDao;


    public List<CompetitionEntity> retrieveAllMatchByCompetition(){
        List<CompetitionEntity> listeCompetitions = new ArrayList<>();
        CompetitionList competitionList = jfdataManager.getAllCompetitions();

        return listeCompetitions;
    }


}
