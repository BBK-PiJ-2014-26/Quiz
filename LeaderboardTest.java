import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.util.GregorianCalendar;

/**
 * Tests the class LeaderboardImpl and the interface Leaderboard.
 * @author Gareth Moore
 */
public class LeaderboardTest {
	
	//A Leaderboard object to be used in testing.
	LeaderboardImpl testLeaderboard;
	
	/**
	 * Instantiates the testLeaderboard.
	 */
	@Before
	public void buildUp() {
		testLeaderboard = new LeaderboardImpl();
		//Adds four attempts. All use today's date for simplicity.
		Attempt a = new AttemptImpl("Constantius", 8);
		testLeaderboard.add(a);
		a = new AttemptImpl("Constantine IX", 4);
		testLeaderboard.add(a);
		a = new AttemptImpl("Valens", 2);
		testLeaderboard.add(a);
		a = new AttemptImpl("Basil", 6);
		testLeaderboard.add(a);
	}
	
	/**
	 * Verrifies that the Leaerboard is organised correctly.
	 * The list must be ordered with highest score first and then in descending order.
	 */
	@Test
	public void shouldBeInScoreOrder() {
		for (int i = 0; i < (testLeaderboard.size() - 1); i++) {
			//Retreives two attempts from the leaderboard.
			Attempt a1 = testLeaderboard.get(i);
			Attempt a2 = testLeaderboard.get(i + 1);
			//Compares the score of both attempts.
			//The lower index should have a greater or equal score than the smaller index.
			assertTrue(a1.getScore() >= a2.getScore());
		}
	}
	
	/**
	 * Verfies that Attempts can be added to an empty Leaderbooard.
	 * After adding an Attempt, the Leaderboard should have size of 1.
	 */
	@Test
	public void shouldAddAttemptToEmptyLeaderboard() {
		//Instantiates an empty Leaderboard.
		LeaderboardImpl empty = new LeaderboardImpl();
		empty.add(new AttemptImpl("Theodora", 12));
		assertTrue(empty.size() == 1);
	}
	
	/**
	 * Verfies that an exception is thrown when a Null Attempt is added.
	 */
	@Test (expected = NullPointerException.class)
	public void shouldThrowExceptionWhenNullAttemptIsAdded() {
		testLeaderboard.add(null);
	}
	
	/**
	 * Boundary case fopr add(Attempt) is when the added score is less than all other scores on the leaderboard.
	 * In that event, the Attempt should be added last to the list.
	 * this test verifies that an Atempt with score 1 is added last and the size of the Leaderboard is 5.
	 */
	@Test
	public void shouldAddScore1Last() {
		Attempt test = new AttemptImpl("Basil", 1);
		testLeaderboard.add(test);
		assertEquals(5, testLeaderboard.size());
		//The newly added Attempt should lie at index 4.
		Attempt actual = testLeaderboard.get(4);
		//Verfies the name is correct.
		assertEquals("Basil", actual.getUserName());
		//Verfies the score is correct.
		assertEquals(1, actual.getScore());
	}
}
