package test.standing;

import fr.pronofoot.jfdata.manager.JfdataManager;
import fr.pronofoot.jfdata.model.standing.Standing;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * JUnit class test for Standing
 * All Standing methods are tested in this class
 * @author remimarion
 */
public class StandingTest {

	@Test
	public void testGetStanding() {
		JfdataManager jfdataManager = new JfdataManager();
		Standing actual = jfdataManager.getStanding(2015);
		
		assertNotNull(actual);
		assertNotNull(actual.getStandings());
	}

}
