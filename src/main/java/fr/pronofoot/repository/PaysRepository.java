package fr.pronofoot.repository;

import fr.pronofoot.entity.PaysEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaysRepository extends JpaRepository<PaysEntity, Long>{

    PaysEntity findOnePaysByNomPays(String nomPays);

}