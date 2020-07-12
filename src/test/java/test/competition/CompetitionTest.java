package test.competition;

import fr.pronofoot.jfdata.manager.JfdataManager;
import fr.pronofoot.jfdata.model.competition.Competition;
import fr.pronofoot.jfdata.model.competition.CompetitionList;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * JUnit class test for Competition
 * All Competition methods are tested in this class
 * @author remimarion
 */
public class CompetitionTest {

	@Test
	public void testGetAllCompetition() {
		JfdataManager jfdataManager = new JfdataManager();
		CompetitionList actual = jfdataManager.getAllCompetitions();
		
		assertNotNull(actual);
		
		for (Competition competitionModel : actual.getCompetitions()) {
			assertNotNull(competitionModel.getId());
		}
	}

	@Test
	public void testGetCompetition() {
		JfdataManager jfdataManager = new JfdataManager();
		Competition actual = jfdataManager.getCompetition(2015);
		
		assertNotNull(actual);
		assertNotNull(actual.getId());
	}
}
