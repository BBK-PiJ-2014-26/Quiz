import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests the class QuizImpl and the interface QuizTest.
 */
public class QuizTest {
	
	Quiz testQuiz;
	//A Quiz object for use in the following tests.
	
	/**
	 * Instantiates testQuiz.
	 */
	@Before
	public void buildUp() {
		List<Question> questions = ArrayList<Question>();
		questions.add(new QuestionImpl("Where is the Hagia Sophia?", new String[] {"Constantinople", "Rome". "Athens", "Antioch"}, 1);
		testQuiz =  new QuizImpl(questions, "Justinian", "The Byzantine Quiz");
	}
	
	/**
	 * Tests setQuizId() and getQuizId().
	 * 
	 * Should return 1.
	 */
	@Test
	public void shouldReturnQuizId1() {
		testQuiz.setQuizId(1);
		assertEquals(1, testQuiz.getQuizId());
	}
	
	/**
	 * Tests terminate() and isTerminated().
	 *
	 * Should return true after quiz has been terminated.
	 */
	@Test
	public void shouldReturnTrueAfterTerminated() {
		testQuiz.terminate();
		assertTrue(testQuiz.isTerminated());
	}
	
	/**
	 * Tests addNewQuestion().
	 *
	 * testQuiz contains one question already. 
	 * After a question has been added, the size of the List of Questions should be 2.
	 */
	@Test
	public void shouldCreateQuestionListOfSize2() {
		testQuiz.addNewQuestion("Who founded Constantinople?", new String[] {"Valens", "Justin", "Diocletian", "Constantine"}, 4);
		assertEquals(2, testQuiz.questions.size());
	}
	
	/**
	 * Tests getQuestions().
	 *
	 * Should return a List containing one question. 
	 * Verifies that this question is expected by checking the String question within it.
	 */
	@Test 
	public void shouldReturnAListContainingOneQuestion() {
		List<Question> actualList = testQuiz.getQuestions();
		//The list contains one question which therefore must lie at index 0.
		Question actualQ = actualList.get(0);
		assertEquals("Where is the Hagia Sophia?", actualQ.getQuestion());
	}
	
	/**
	 * Tests getAuthor().
	 *
	 * Should return "Justinian".
	 */
	@Test
	public void shouldReturnAuthorJustinian() {
		assertEquals("Justinian", testQuiz.getAuthor());
	}
	
	/**
	 * Tests getName().
	 *
	 * Should return "Justinian".
	 */
	@Test
	public void shouldReturnAuthorJustinian() {
		assertEquals("The Byzantine Quiz", testQuiz.getName());
	}
	
	//Need tests for addNewAttempt() and getLeaderboard()
}
