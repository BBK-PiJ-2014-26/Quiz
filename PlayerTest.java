import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the clas PlayerImpl and the interface Player.
 */
public class PlayerTest {
	
	Player testPlayer;
	//A Player object for use in testing.
	
	/**
	 * Tests setUserName() and getUserName().
	 *
	 * Should return Justinian.
	 */
	@Test
	public void shouldReturnJustinian() {
		testPlayer = new PlayerImpl();
		testPlayer.setUserName("Justinian");
		assertEquals("Justinian", testPlayer.getUserName());
	}
}
