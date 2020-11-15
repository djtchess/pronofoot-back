package fr.pronofoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.pronofoot.entity.CompetitionEntity;

@Repository
public interface CompetitionRepository extends JpaRepository<CompetitionEntity, Long>{

    public CompetitionEntity findOneCompetitionByApiId(Long apiId);
    public CompetitionEntity findOneCompetitionById(Long Id);

}