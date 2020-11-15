package fr.pronofoot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pronofoot.entity.CompetitionEntity;
import fr.pronofoot.entity.CompetitionTeamEntity;
import fr.pronofoot.entity.SaisonEntity;
import fr.pronofoot.entity.TeamEntity;
import fr.pronofoot.repository.CompetitionRepository;
import fr.pronofoot.repository.CompetitionTeamRepository;

@Component
public class CompetitionDao {
 
    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private CompetitionTeamRepository competitionTeamRepository;

    public List<CompetitionEntity> listCompetitions() {
        return competitionRepository.findAll();
    }

    public CompetitionEntity getCompetitionByApiId(Long apiId) {
        return competitionRepository.findOneCompetitionByApiId(apiId);
    }

    public CompetitionEntity getCompetitionById(Long id) {
        return competitionRepository.findOneCompetitionById(id);
    }
 
    public void saveAllCompetitions(List<CompetitionEntity> competitions){
        competitionRepository.saveAll(competitions);
    }

    public boolean isCompetitionForCompetitionTeamSaison(CompetitionEntity competition, TeamEntity team, SaisonEntity saison){
        CompetitionTeamEntity competitionTeamEntity = competitionTeamRepository.findByCompetitionEntityAndTeamEntityAndSaisonEntity(competition, team, saison);
        return (competitionTeamEntity == null) ? false : true;
    }
 
}