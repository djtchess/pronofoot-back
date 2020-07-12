package test.team;

import fr.pronofoot.jfdata.manager.JfdataManager;
import fr.pronofoot.jfdata.model.team.Team;
import fr.pronofoot.jfdata.model.team.TeamList;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * JUnit class test for Team
 * All Team methods are tested in this class
 * @author remimarion
 */
public class TeamTest {

	@Test
	public void testGetTeamsByCompetition() {
		JfdataManager jfdataManager = new JfdataManager();
		TeamList actual = jfdataManager.getTeamsByCompetition(2015);
		
		assertNotNull(actual);
		assertNotNull(actual.getTeams());
	}
	
	@Test
	public void testGetTeam() {
		JfdataManager jfdataManager = new JfdataManager();
		Team actual = jfdataManager.getTeam(18);
		
		assertNotNull(actual);
		assertNotNull(actual.getName());
	}
}
