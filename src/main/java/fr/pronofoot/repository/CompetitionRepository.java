package fr.pronofoot.repository;

import fr.pronofoot.entity.CompetitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<CompetitionEntity, Long>{

    public CompetitionEntity findOneCompetitionByApiId(Long apiId);

}