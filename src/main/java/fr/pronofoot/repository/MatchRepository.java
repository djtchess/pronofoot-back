package fr.pronofoot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.pronofoot.entity.CompetitionEntity;
import fr.pronofoot.entity.MatchEntity;
import fr.pronofoot.entity.SaisonEntity;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Long>{

    MatchEntity findMatchByApiId(Long id);
    List<MatchEntity> getAllMatchByCompetitionEntityAndSaisonEntity(CompetitionEntity competitionEntity, SaisonEntity saisonEntity);


}