import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.util.GregorianCalendar;

/**
 * Tests the class LeaderboardImpl and the interface Leaderboard.
 */
public class LeaderboardTest {
	
	//A Leaderboard object to be used in testing.
	Leaderboard<Attempt> testLeaderboard;
	
	/**
	 * Instantiates the testLeaderboard.
	 */
	@Before
	public void buildUp() {
		testLeaderboard = LeaderboardImpl<Attempt>();
		//Adds five attempts. All use today's date for simplicity.
		testLeaderboard.add(new AttemptImpl("Constantius", 8, new GregorianCalendar());
		testLeaderboard.add(new AttemptImpl("Constantine IX", 4, new GregorianCalendar());
		testLeaderboard.add(new AttemptImpl("Valens", 2, new GregorianCalendar());
		testLeaderboard.add(new AttemptImpl("Basil", 6, new GregorianCalendar());
		testLeaderboard.add(new AttemptImpl("Justinian", 10, new GregorianCalendar());
	}
}
