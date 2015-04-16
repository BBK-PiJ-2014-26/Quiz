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
		List<Question> questions = new ArrayList<Question>();
		questions.add(new QuestionImpl("Where is the Hagia Sophia?", new String[] {"Constantinople", "Rome", "Athens", "Antioch"}, 1));
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
	 * Tests addNewQuestion() and getQuestions().
	 *
	 * testQuiz contains one question already. 
	 * After a question has been added, the size of the List of Questions should be 2.
	 */
	@Test
	public void shouldCreateQuestionListOfSize2() {
		testQuiz.addNewQuestion("Who founded Constantinople?", new String[] {"Valens", "Justin", "Diocletian", "Constantine"}, 3);
		List<Question> actualList = testQuiz.getQuestions();
		assertEquals(2, actualList.size());
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
	 * Should return "The Byzantine Quiz".
	 */
	@Test
	public void shouldReturnNameByzantineQuiz() {
		assertEquals("The Byzantine Quiz", testQuiz.getName());
	}

	/**
	 * Tests addNewAttempt() and getLeaderboard().
	 *
	 * Should add a new Attempt to the instance variable leaderBoard.
	 * To valiidate this action worked correctly, getLeaderBoard() should 
	 * return a List of size 1.
	 */
	@Test
	public void shouldReturnLeaderboardOfSize1() {
		//Adds a new attempt. As the list had zero attempts, the list will now have one element.
		testQuiz.addNewAttempt("Constantine IX", 6, new GregorianCalendar(2014, 10, 10, 10, 30));
		List<Attempt> actualLeaderboard = testQuiz.getLeaderboard();
		assertEquals(1, actualLeaderboard.size());
	}
	
	/**
	 * Tests getLeaderboard().
	 *
	 * Should return an empty list because no attempts have been added to testQuiz.
	 */
	@Test
	public void shouldReturnEmptyLeaderboard() {
		List<Attempt> actualLeaderboard = testQuiz.getLeaderboard();
		assertTrue(actualLeaderboard.isEmpty());
	}	
	
}
