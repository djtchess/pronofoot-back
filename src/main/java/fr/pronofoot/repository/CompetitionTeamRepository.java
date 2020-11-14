package fr.pronofoot.repository;

import fr.pronofoot.entity.CompetitionEntity;
import fr.pronofoot.entity.CompetitionTeamEntity;
import fr.pronofoot.entity.SaisonEntity;
import fr.pronofoot.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionTeamRepository extends JpaRepository<CompetitionTeamEntity, Long>{

    CompetitionTeamEntity findByCompetitionEntityAndTeamEntityAndSaisonEntity(CompetitionEntity competitionEntity, TeamEntity teamEntity, SaisonEntity saisonEntity);
}