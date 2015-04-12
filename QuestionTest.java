import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Tests the class QuestionImpl and the interface Question.
 */
public class QuestionTest {

	Question testQuestion;
	//A Question object for use in the following tests.
	
	/**
	 * Instantiates the testQuestion object.
	 */
	@Before
	public void buildUp() {
		String question = "What was the capital of the Byzantine empire?";
		String[] possibleAnswers = new String[] {Constantinople, Rome, Odessa, Ephesus};
		int correctAnswer = 1;
		testQuestion = new Question(question, possibleAnswers, correctAnswer);
	}
	
	/**
	 * Tests setOrder() and getOrder().
	 * Should confirm that the correct order number is assigned to the Question.
	 */
	@Test
	public void shouldSetOrderNumberAs1() {
		testQuestion.setOrder(1);
		assertEquals(1, testQuestion.getOrder());
	}
	
	/**
	 * Tests setOrder().
	 * Should throw an exception if zero is the argument.
	 */
	@Test
	public void shouldThrowExceptionWhenOrderIs0() {
		boolean exceptionThrown = false;
		try {
			testQuestion.setOrder(0);
		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}
	
	/**
	 * Tests setOrder().
	 * Should throw an exception if zero is thrown as an exception.
	 */
	@Test
	public void shouldThrowExceptionWhenOrderIsNegative() {
		boolean exceptionThrown = false;
		try {
			testQuestion.setOrder(-4);
		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}
	
	/**
	 * Tests getQuestion().
	 * Should return the correct question.
	 */
	@Test
	public void shouldReturnCorrectQuestion() {
		assertEquals("What was the capital of the Byzantine empire?", testQuestion.getQuestion());
	}

	/**
	 * Tests getPossibleAnswers().
	 * Should return the correct possible answers.
	 */
	@Test
	public void shouldReturnCorrectPossibleAnswers() {
		assertArrayEquals(possibleAnswers, testQuestion.getPossibleAnswers());
	}
	
	/**
	 * Tests getCorrectAnswer().
	 * Should return the int which represent the index where the correct answers lies.
	 */
	@Test
	public void shouldReturnCorrectAnswerInt() {
		assertEquals(1, testQuestion.getCorrectAnswer());
	}
}
