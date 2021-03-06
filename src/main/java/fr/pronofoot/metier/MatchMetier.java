package fr.pronofoot.metier;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.pronofoot.dao.CompetitionDao;
import fr.pronofoot.dao.MatchDao;
import fr.pronofoot.dao.TeamDao;
import fr.pronofoot.entity.CompetitionEntity;
import fr.pronofoot.entity.MatchEntity;
import fr.pronofoot.entity.TeamEntity;
import fr.pronofoot.jfdata.manager.JfdataManager;
import fr.pronofoot.jfdata.model.match.Match;
import fr.pronofoot.jfdata.model.match.MatchList;
import fr.pronofoot.model.MatchModel;

@Service
public class MatchMetier extends BaseMetier {

    @Autowired
    private JfdataManager jfdataManager;

    @Autowired
    private MatchDao matchDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private CompetitionDao competitionDao;

    public void getClassementByCompetition(Long id){
        CompetitionEntity competitionEntity = competitionDao.getCompetitionById(id);
        competitionEntity.getCompetitionTeamEntityList().stream()
                .filter(competitionTeamEntity -> competitionTeamEntity.getSaisonEntity().equals(competitionEntity.getCurrentSaison()))
        .forEach(competitionTeamEntity -> {
            System.out.println("*********** "+ competitionTeamEntity.getTeamEntity().getShortName()+" **********");
            matchDao.getAllMatchByTeamFinished(competitionEntity, competitionTeamEntity.getTeamEntity());
        });

    }

    public List<MatchModel> getMatchsByTeamForCompetition(Long idCompetition, Long idTeam){
        List<MatchModel> matchModelList = new ArrayList<>();
        CompetitionEntity competitionEntity = competitionDao.getCompetitionById(idCompetition);
        TeamEntity teamEntity = teamDao.getTeamById(idTeam);
        matchDao.getAllMatchByTeam(competitionEntity, teamEntity).forEach(matchEntity -> {
            matchModelList.add(convertMatchEntityToMatchModel(matchEntity));
        });
        return matchModelList;
    }

    public List<MatchModel> getAllMatchByCompetition(Long id){
        getClassementByCompetition(id);
        List<MatchModel> matchModelList = new ArrayList<>();
        CompetitionEntity competitionEntity = competitionDao.getCompetitionById(id);
        matchDao.getAllMatchByCompetition(competitionEntity).forEach(matchEntity -> {
            matchModelList.add(convertMatchEntityToMatchModel(matchEntity));
        });
        return  matchModelList;
    }

    public List<MatchEntity> retrieveAllMatchByCompetition(Long id){
        List<MatchEntity> matchEntities = new ArrayList<>();
        JfdataManager jfdataManager = new JfdataManager();
        MatchList matchList = jfdataManager.getMatchesByCompetition(id.intValue());
        CompetitionEntity competitionEntity = competitionDao.getCompetitionByApiId(Long.valueOf(matchList.getCompetition().getId()));
        matchList.getMatches().forEach(match -> {
                matchEntities.add(convertMatchToMatchEntity(match, competitionEntity));
        });
        matchDao.saveAllMatches(matchEntities);
        return matchEntities;
    }

    public List<MatchEntity> retrieveAllMatchByCompetitionByDay(Long id, String day){
        List<MatchEntity> matchEntities = new ArrayList<>();
        JfdataManager jfdataManager = new JfdataManager();
        MatchList matchList = jfdataManager.getMatchesByCompetition(id.intValue());
        CompetitionEntity competitionEntity = competitionDao.getCompetitionByApiId(Long.valueOf(matchList.getCompetition().getId()));
        matchList.getMatches().stream()
                .filter(match -> match.getMatchday().equals(day))
                .map(match -> {
                    match.getScore().getFullTime().setAwayTeam("0");
                    match.getScore().getFullTime().setHomeTeam("0");
                    match.setStatus("SCHEDULED");
                    return match;
                })
                .collect(Collectors.toList())
                .forEach(match -> {
            matchEntities.add(convertMatchToMatchEntity(match, competitionEntity));
        });
        matchDao.saveAllMatches(matchEntities);
        return matchEntities;
    }

    private MatchEntity convertMatchToMatchEntity(Match match, CompetitionEntity competitionEntity){
        MatchEntity matchEntity = matchDao.getMacthByApiId(Long.valueOf(match.getId()));
        if (matchEntity == null){
            matchEntity = new MatchEntity();
            matchEntity.setApiId(Long.valueOf(match.getId()));
        }

        TeamEntity homeTeam = teamDao.getTeamByApiId(Long.valueOf((match.getHomeTeam().getId())));
        TeamEntity awayTeam = teamDao.getTeamByApiId(Long.valueOf((match.getAwayTeam().getId())));
        if (competitionEntity != null){
            matchEntity.setSaisonEntity(competitionEntity.getCurrentSaison());
            matchEntity.setCompetitionEntity(competitionEntity);
        }
        matchEntity.setAwayTeam(awayTeam);
        matchEntity.setHomeTeam(homeTeam);
        matchEntity.setDateMaj(new java.util.Date());
        matchEntity.setStatus(match.getStatus());
        if (match.getScore().getFullTime().getHomeTeam() != null){
            matchEntity.setButHomeFull(Integer.valueOf(match.getScore().getFullTime().getHomeTeam()));
            matchEntity.setButAwayFull(Integer.valueOf(match.getScore().getFullTime().getAwayTeam()));
        }
        if (match.getMatchday() != null) matchEntity.setMatchDay(Integer.valueOf(match.getMatchday()));
        if (match.getUtcDate() != null) {
            matchEntity.setUtcDate(Date.valueOf(match.getUtcDate().substring(0,10)));
        }
        return  matchEntity;

    }

    private MatchModel convertMatchEntityToMatchModel(MatchEntity entity){
        return map(entity, MatchModel.class);
    }

}
