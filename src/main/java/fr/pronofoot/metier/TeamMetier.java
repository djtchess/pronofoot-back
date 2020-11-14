package fr.pronofoot.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.pronofoot.dao.CompetitionDao;
import fr.pronofoot.dao.TeamDao;
import fr.pronofoot.entity.CompetitionEntity;
import fr.pronofoot.entity.CompetitionTeamEntity;
import fr.pronofoot.entity.TeamEntity;
import fr.pronofoot.jfdata.manager.JfdataManager;
import fr.pronofoot.jfdata.model.team.Team;
import fr.pronofoot.jfdata.model.team.TeamList;
import fr.pronofoot.model.TeamModel;

@Service
public class TeamMetier extends BaseMetier{

     @Autowired
     private TeamDao teamDao;

     @Autowired
     private CompetitionDao competitionDao;

    @Autowired
    private JfdataManager jfdataManager;

    public List<TeamModel> createAllTeamsByCompetition(Long idCompetition){
        List<TeamEntity> teamEntities = retrieveTeamsByCompetition(idCompetition);
        saveAllTeams(teamEntities);
        List<TeamModel> teams = new ArrayList<>();
        teamEntities.forEach(teamEntity -> teams.add(map(teamEntity, TeamModel.class)));
        return teams;
    }

    private void saveAllTeams(List<TeamEntity> teams){
        this.teamDao.saveAllTeams(teams);
    }

    private List<TeamEntity> retrieveTeamsByCompetition(Long idCompetition){
        List<TeamEntity> listeTeams = new ArrayList<>();
        TeamList teamList = jfdataManager.getTeamsByCompetition(idCompetition.intValue());
        CompetitionEntity competitionEntity = competitionDao.getCompetitionById(Long.valueOf(teamList.getCompetition().getId()));
        teamList.getTeams().forEach(team -> {
            TeamEntity entity = teamDao.getTeamByName(team.getName());
            if (entity == null) {
                entity =  convertTeamToTeamEntity(team);
                entity.setPays(competitionEntity.getPays());
            }
            if (!isCompetitonSaisonFounded(competitionEntity, entity)){
                entity.addCompetition(competitionEntity);
            }
            listeTeams.add(entity);
        });
        return listeTeams;
    }

    private boolean isCompetitonSaisonFounded(CompetitionEntity competitionEntity, TeamEntity entity) {
        List<CompetitionTeamEntity> liste = entity.getCompetitionTeamEntityList()
                .stream()
                .filter((competitionTeamEntity -> competitionTeamEntity.getCompetitionEntity().equals(competitionEntity)))
                .collect(Collectors.toList());
        if (liste.isEmpty()) return false;
        else return true;
    }

    private TeamEntity convertTeamToTeamEntity(Team team){
        TeamEntity teamEntity = mapToEntity(team, TeamEntity.class);
        teamEntity.setApiId(Long.valueOf(team.getId()));
        return  teamEntity;
    }



}
