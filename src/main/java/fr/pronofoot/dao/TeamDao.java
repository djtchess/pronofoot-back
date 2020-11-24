package fr.pronofoot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pronofoot.entity.TeamEntity;
import fr.pronofoot.repository.CompetitionTeamRepository;
import fr.pronofoot.repository.TeamRepository;

@Component
public class TeamDao {
 
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private CompetitionTeamRepository competitionTeamRepository;

    public List<TeamEntity> listTeams() {
        return teamRepository.findAll();
    }
 
    public void saveAllTeams(List<TeamEntity> teams){
        teamRepository.saveAll(teams);
    }

    public TeamEntity getTeamByName(String name){
        return teamRepository.findTeamByName(name);
    }

    public TeamEntity getTeamByApiId(Long id){
        return teamRepository.findTeamByApiId(id);
    }
 
}