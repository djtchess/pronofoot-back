package fr.pronofoot.csv;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.pronofoot.metier.CompetitionMetier;

@RestController
@RequestMapping(value="/pronofoot")
public class CsvController {

    @Autowired
    private CompetitionMetier competitionMetier;


    @GetMapping("/teams/export")
    public String exportCSV() throws Exception {

        List<PaysBean> paysBeanList = new ArrayList<>();
        competitionMetier.getAllPays().forEach(paysEntity -> paysBeanList.add(new DozerBeanMapper().map(paysEntity, PaysBean.class)));
        return ExportCSV.createCsv(paysBeanList, PaysBean.class);

    }
}
