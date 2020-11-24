package fr.pronofoot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pronofoot.entity.CompetitionEntity;
import fr.pronofoot.entity.MatchEntity;
import fr.pronofoot.repository.MatchRepository;

@Component
public class MatchDao {
 
    @Autowired
    private MatchRepository matchRepository;


    public List<MatchEntity> listMatches() {
        return matchRepository.findAll();
    }
 
    public void saveAllMatches(List<MatchEntity> macthes){
        matchRepository.saveAll(macthes);
    }

    public MatchEntity getMacthByApiId(Long id){
        return matchRepository.findMatchByApiId(id);
    }

    public List<MatchEntity> getAllMatchByCompetition(CompetitionEntity competitionEntity) {
        return matchRepository.getAllMatchByCompetitionEntityAndSaisonEntity(competitionEntity, competitionEntity.getCurrentSaison());
    }
 
}