package fr.pronofoot.dao;

import fr.pronofoot.entity.PaysEntity;
import fr.pronofoot.repository.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaysDao {
 
    @Autowired
    private PaysRepository paysRepository;

    public List<PaysEntity> listPays() {
        return paysRepository.findAll();
    }

    public PaysEntity getPaysById(Long id) {
        return paysRepository.getOne(id);
    }
 
    public void saveAllPays(List<PaysEntity> pays){
        paysRepository.saveAll(pays);
    }

    public PaysEntity getPaysByName(String name){
        return paysRepository.findOnePaysByNomPays(name);
    }

 
}