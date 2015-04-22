import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.util.GregorianCalendar;

/**
 * Tests the class LeaderboardImpl and the interface Leaderboard.
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
		//Adds five attempts. All use today's date for simplicity.
		testLeaderboard.add(new AttemptImpl("Constantius", 8, new GregorianCalendar()));
		testLeaderboard.add(new AttemptImpl("Constantine IX", 4, new GregorianCalendar()));
		testLeaderboard.add(new AttemptImpl("Valens", 2, new GregorianCalendar()));
		testLeaderboard.add(new AttemptImpl("Basil", 6, new GregorianCalendar()));
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
		empty.add(new AttemptImpl("Theodora", 12, new GregorianCalendar()));
		assertTrue(empty.size() == 1);
	}
	
	/**
	 * Verfies that an exception is thrown when a Null Attempt is added.
	 */
	@Test (expected = NullPointerException.class)
	public void shouldThrowExceptionWhenNullAttemptIsAdded() {
		testLeaderboard.add(null);
	}
}
