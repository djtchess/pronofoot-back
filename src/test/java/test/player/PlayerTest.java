package test.player;

import fr.pronofoot.jfdata.manager.JfdataManager;
import fr.pronofoot.jfdata.model.player.Player;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * JUnit class test for Player
 * All Player methods are tested in this class
 * @author remimarion
 */
public class PlayerTest {

	@Test
	public void testGetPlayer() {
		JfdataManager jfdataManager = new JfdataManager();
		Player actual = jfdataManager.getPlayer(44);
		
		assertNotNull(actual);
		assertNotNull(actual.getName());
	}
}
