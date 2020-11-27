package fr.pronofoot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.pronofoot.entity.CompetitionEntity;
import fr.pronofoot.entity.MatchEntity;
import fr.pronofoot.entity.SaisonEntity;
import fr.pronofoot.entity.TeamEntity;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Long>{

    MatchEntity findMatchByApiId(Long id);
    List<MatchEntity> getAllMatchByCompetitionEntityAndSaisonEntity(CompetitionEntity competitionEntity, SaisonEntity saisonEntity);

    @Query("select me from MatchEntity me where me.competitionEntity =:competitionEntity and me.saisonEntity =:saisonEntity and (me.awayTeam =:awayTeam or me.homeTeam =:homeTeam) order by me.utcDate")
    List<MatchEntity> getAllMatchByTeam(CompetitionEntity competitionEntity, SaisonEntity saisonEntity, TeamEntity awayTeam, TeamEntity homeTeam);

    @Query("select me from MatchEntity me where me.competitionEntity =:competitionEntity and me.saisonEntity =:saisonEntity and (me.awayTeam =:awayTeam or me.homeTeam =:homeTeam)"
    +" and me.status= 'FINISHED'")
    List<MatchEntity> getAllMatchFinishedByTeam(CompetitionEntity competitionEntity, SaisonEntity saisonEntity, TeamEntity awayTeam, TeamEntity homeTeam);

}