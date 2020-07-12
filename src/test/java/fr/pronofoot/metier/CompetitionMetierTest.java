package fr.pronofoot.metier;

import fr.pronofoot.entity.PaysEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompetitionMetierTest {

	@Autowired
	private CompetitionMetier cm;

	@Test
	public void testRetrievePays() {
		List<PaysEntity> liste = cm.retrievePays();
		System.out.println(liste.size());
	}


}
