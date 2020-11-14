package fr.pronofoot.repository;

import fr.pronofoot.entity.SaisonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface SaisonRepository extends JpaRepository<SaisonEntity, Long>{

    SaisonEntity findSaisonByDateDebutAndDateFin(Date dateDebut, Date dateFin);


}