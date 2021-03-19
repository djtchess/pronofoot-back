package fr.pronofoot.metier;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.pronofoot.dao.CompetitionDao;
import fr.pronofoot.dao.MatchDao;
import fr.pronofoot.entity.CompetitionEntity;
import fr.pronofoot.entity.CompetitionTeamEntity;
import fr.pronofoot.entity.MatchEntity;
import fr.pronofoot.entity.SaisonEntity;
import fr.pronofoot.entity.TeamEntity;
import fr.pronofoot.model.StandingTeamModel;
import fr.pronofoot.model.StandingTypeModel;
import fr.pronofoot.model.TeamModel;

@Service
public class StandingMetier extends BaseMetier {

    @Autowired
    private MatchDao matchDao;

    @Autowired
    private CompetitionDao competitionDao;


    public void getClassement(Long idCompetition){
        StandingTypeModel totalStanding = new StandingTypeModel();
        CompetitionEntity competitionEntity = competitionDao.getCompetitionById(idCompetition);
        SaisonEntity currentSaison = competitionEntity.getCurrentSaison();
        List<CompetitionTeamEntity> teamList = competitionEntity.getCompetitionTeamEntityList().stream()
                .filter(competitionTeamEntity -> competitionTeamEntity.getSaisonEntity().equals(competitionEntity.getCurrentSaison()))
                .collect(Collectors.toList());
         teamList.forEach(competitionTeamEntity -> {
                TeamEntity teamEntity = competitionTeamEntity.getTeamEntity();
                List<MatchEntity> matchEntityList = matchDao.getAllMatchByTeamFinished(competitionEntity, competitionTeamEntity.getTeamEntity());
                StandingTeamModel standingTeamModel = new StandingTeamModel();
                standingTeamModel.setTeam(map(teamEntity, TeamModel.class));
                matchEntityList.forEach( match -> {
                    if (match.getHomeTeam().equals(teamEntity)){
                        if (match.getButHomeFull() > match.getButAwayFull()){
                            standingTeamModel.add3Points();
                            standingTeamModel.addWon();
                        } else  if (match.getButHomeFull() == match.getButAwayFull()){
                            standingTeamModel.add1Points();
                            standingTeamModel.addDraw();
                        } else {
                            standingTeamModel.addLost();
                        }
                        standingTeamModel.addGoalsFor(match.getButHomeFull());
                        standingTeamModel.addGoalsAgainst(match.getButAwayFull());
                    }else {
                        if (match.getButAwayFull() > match.getButHomeFull()){
                            standingTeamModel.add3Points();
                            standingTeamModel.addWon();
                        } else  if (match.getButHomeFull() == match.getButAwayFull()){
                            standingTeamModel.add1Points();
                            standingTeamModel.addDraw();
                        } else {
                            standingTeamModel.addLost();
                        }
                        standingTeamModel.addGoalsAgainst(match.getButHomeFull());
                        standingTeamModel.addGoalsFor(match.getButAwayFull());

                    }
                    standingTeamModel.addPlayedGames();

                });
                totalStanding.addStandingTeamModel(standingTeamModel);
            });
        totalStanding.getStanding().sort(Comparator.comparing(StandingTeamModel::getPoints)
                .thenComparing(StandingTeamModel::getGoalDifference)
                .thenComparing(StandingTeamModel::getGoalsFor).reversed());
        totalStanding.getStanding().forEach(standingTeamModel ->{
            System.out.println(standingTeamModel.getTeam().getShortName() + " "+ standingTeamModel.getPoints() + "pts "+ standingTeamModel.getGoalDifference()+ " diff "+ standingTeamModel.getGoalsFor() + " bp");
        } );

    }


}
