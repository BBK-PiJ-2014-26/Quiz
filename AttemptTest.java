import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.util.GregorianCalendar;
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
		Double score = 6.0;
		Calendar date = new GregorianCalendar(2015, 10, 10, 10, 15);
		//For simplicity, creates a calendar object containing todays's date.
		testAttempt = new AttemptImpl(userName, score, date);
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
	 * Should demonstrate that the returned value matches 6.0.
	 */
	@Test
	public void shouldReturn6() {
		assertEquals(6.0, testAttempt.getScore(), 0.0);
	}
	
	/**
	 * Tests getDate().
	 *
	 * Should demonstrate that the returned value matches "Justinian".
	 */
	@Test
	public void shouldReturn10Oct2015() {
		Calendar expected = new GregorianCalendar(2015, 10, 10, 10, 15);
		assertEquals(expected, testAttempt.getDate());
	}
}
