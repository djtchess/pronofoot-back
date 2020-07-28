package fr.pronofoot.dao;

import fr.pronofoot.entity.CompetitionEntity;
import fr.pronofoot.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompetitionDao {
 
    @Autowired
    private CompetitionRepository competitionRepository;

    public List<CompetitionEntity> listCompetitions() {
        return competitionRepository.findAll();
    }
 
    public void saveAllCompetitions(List<CompetitionEntity> competitions){
        competitionRepository.saveAll(competitions);
    }
 
}