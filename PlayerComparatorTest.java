import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Tests PlayerComparator.
 */
public class PlayerComparatorTest {
	
	//Player objects to be used in the following tests.
	private Player player1;
	private Player player2;
	//A PlayerComparator for testing.
	private PlayerComparator myComp;
	
	/**
	 * Initialises player1 with a userName and player2 without.
	 * Initialises myComp.
	 */
	@Before
	public void buildUp() {
		player1 = new PlayerImpl();
		player2 = new PlayerImpl();
		player1.setUserName("Justinian");
		myComp = new PlayerComparator();
	}
	
	/**
	 * Should return zero.
	 */
	@Test
	public void shouldReturnZero() {
		player2.setUserName("Justinian");
		assertEquals(0, myComp.compare(player1, player2));
	}
	
	/**
	 * Should return a negative number.
	 */
	@Test
	public void shouldReturnNegativeNumber() {
		player2.setUserName("Valens");
		assertTrue(myComp.compare(player1, player2) < 0);
	}	

	/**
	 * Should return a positive number.
	 */
	@Test
	public void shouldReturnPositiveNumber() {
		player2.setUserName("Constantine IX");
		assertTrue(myComp.compare(player1, player2) > 0);
	}	
}
