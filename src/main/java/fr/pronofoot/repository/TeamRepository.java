package fr.pronofoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.pronofoot.entity.TeamEntity;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long>{

    TeamEntity findTeamByName(String name);
    TeamEntity findTeamByApiId(Long id);


}