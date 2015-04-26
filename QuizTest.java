import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests the class QuizImpl and the interface QuizTest.
 * @author Gareth Moore
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
		Attempt a = new AttemptImpl("Constantine IX", 6);
		testQuiz.addNewAttempt(a);
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
	
	/**
	 * Tests addNewQuestion().
	 *
	 * Should throw exception if question is null.
	 */
	@Test
	public void shouldThrowExceptionIfQuestionIsNull() {
		boolean exceptionThrown = false;
		try {
			testQuiz.addNewQuestion(null, new String[] {"Valens", "Justin", "Diocletian", "Constantine"}, 3);
		} catch (Exception e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}

	/**
	 * Tests addNewQuestion().
	 *
	 * Should throw exception if possibleAnswers is null.
	 */
	@Test
	public void shouldThrowExceptionIfPossibleAnswersIsNull() {
		boolean exceptionThrown = false;
		try {
			testQuiz.addNewQuestion("Who founded Constantinople?", null, 3);
		} catch (Exception e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}
	
	/**
	 * Tests addNewQuestion().
	 *
	 * Should throw exception if possibleAnswers has a length of 5.
	 */
	@Test
	public void shouldThrowExceptionIfPossibleAnswersIsOfLength5() {
		boolean exceptionThrown = false;
		try {
			//Adds a Question object containg an array of 5 objects
			testQuiz.addNewQuestion("Who founded Constantinople?", new String[] {"Caligula","Valens", "Justin", "Diocletian", "Constantine"}, 3);
		} catch (Exception e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}
	
	/**
	 * Tests addNewQuestion().
	 *
	 * Should throw exception if correctAnswer is -1.
	 */
	@Test
	public void shouldThrowExceptionIfCorrectAnswerIsMinus1() {
		boolean exceptionThrown = false;
		try {
			//Adds a Question object containg an correctAnswer of -1.
			testQuiz.addNewQuestion("Who founded Constantinople?", new String[] {"Valens", "Justin", "Diocletian", "Constantine"}, -1);
		} catch (Exception e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}
	
	/**
	 * Tests addNewQuestion().
	 *
	 * Should throw exception if correctAnswer is 6.
	 */
	@Test
	public void shouldThrowExceptionIfCorrectAnswerIs6() {
		boolean exceptionThrown = false;
		try {
			//Adds a Question object containg an correctAnswer of 6.
			testQuiz.addNewQuestion("Who founded Constantinople?", new String[] {"Valens", "Justin", "Diocletian", "Constantine"}, 6);
		} catch (Exception e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}
	
	/**
	 * Tests addNewAttempt().
	 *
	 * Should throw exception if attempt is null.
	 */
	@Test (expected = NullPointerException.class)
	public void shouldThrowExceptionIfAttemptIsNull() {
		Attempt a = null;
		testQuiz.addNewAttempt(a);
	}
}
