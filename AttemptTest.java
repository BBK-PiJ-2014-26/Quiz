import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.util.Calendar;

/**
 * Tests the class AttemptImpl and the interface Attempt.
 */
public class AttemptTest {
	
	Attempt testAttempt;
	//An Attempt object for use in the following tests.
	
	/**
	 * Instantiates testAttempt.
	 */
	@Before
	public void buildUp() {
		String userName = "Justinian";
		int score = 6;
		testAttempt = new AttemptImpl(userName, score);
	}
	
	/**
	 * Tests getUserName().
	 *
	 * Should demonstrate that the returned value matches "Justinian".
	 */
	@Test
	public void shouldReturnJustinian() {
		assertEquals("Justinian", testAttempt.getUserName());
	}
	
	/**
	 * Tests getScore().
	 *
	 * Should demonstrate that the returned value matches 6.
	 */
	@Test
	public void shouldReturn6() {
		assertEquals(6, testAttempt.getScore());
	}
}
