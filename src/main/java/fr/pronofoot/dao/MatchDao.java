package fr.pronofoot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pronofoot.entity.CompetitionEntity;
import fr.pronofoot.entity.MatchEntity;
import fr.pronofoot.entity.TeamEntity;
import fr.pronofoot.repository.MatchRepository;
import fr.pronofoot.repository.TeamRepository;

@Component
public class MatchDao {
 
    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;


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

    public List<MatchEntity> getAllMatchByTeamFinished(CompetitionEntity competitionEntity, TeamEntity team) {
        List<MatchEntity> matchEntities = matchRepository.getAllMatchFinishedByTeam(competitionEntity, competitionEntity.getCurrentSaison(),team,team);
        matchEntities.forEach(match->{
            System.out.println(match.getHomeTeam().getShortName() + " - " + match.getAwayTeam().getShortName() + " : "+ match.getButHomeFull() + " - "+match.getButAwayFull());
        });
        return matchEntities;
    }

    public List<MatchEntity> getAllMatchByTeam(CompetitionEntity competitionEntity, TeamEntity team) {
        List<MatchEntity> matchEntities = matchRepository.getAllMatchByTeam(competitionEntity, competitionEntity.getCurrentSaison(),team,team);
        matchEntities.forEach(match->{
            System.out.println(match.getHomeTeam().getShortName() + " - " + match.getAwayTeam().getShortName() + " : "+ match.getButHomeFull() + " - "+match.getButAwayFull());
        });
        return matchEntities;
    }
 
}