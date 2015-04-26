import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the clas PlayerImpl and the interface Player.
 * @author Gareth Moore
 */
public class PlayerTest {
	
	Player testPlayer;
	//A Player object for use in testing.
	
	/**
	 * Tests getUserName().
	 *
	 * Should return Justinian.
	 */
	@Test
	public void shouldReturnJustinian() {
		testPlayer = new PlayerImpl("Justinian");
		assertEquals("Justinian", testPlayer.getUserName());
	}
}
