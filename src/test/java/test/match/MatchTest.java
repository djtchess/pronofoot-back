package test.match;

import fr.pronofoot.jfdata.manager.JfdataManager;
import fr.pronofoot.jfdata.model.match.Match;
import fr.pronofoot.jfdata.model.match.MatchList;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * JUnit class test for Match
 * All Match methods are tested in this class
 * @author remimarion
 */
public class MatchTest {

	@Test
	public void testGetMatch() {
		JfdataManager jfdataManager = new JfdataManager();
		Match actual = jfdataManager.getMatch(238997);
		
		assertNotNull(actual);
		assertNotNull(actual.getScore());
		assertNotNull(actual.getScore().getWinner());
	}
	
	@Test
	public void testGetMatchByCompetition() {
		JfdataManager jfdataManager = new JfdataManager();
		MatchList actual = jfdataManager.getMatchesByCompetition(2015);
		
		assertNotNull(actual);
		assertNotNull(actual.getMatches());
	}
	
	@Test
	public void testGetMatchByTeam() {
		JfdataManager jfdataManager = new JfdataManager();
		MatchList actual = jfdataManager.getMatchesByTeam(527);
		
		assertNotNull(actual);
		assertNotNull(actual.getMatches());
	}
}
