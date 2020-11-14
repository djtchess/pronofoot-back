package fr.pronofoot.repository;

import fr.pronofoot.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long>{

    TeamEntity findTeamByName(String name);


}