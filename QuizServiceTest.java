import java.rmi.Remote;
import java.rmi.RemoteException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import java.util.List;
import java.util.LinkedList;
import java.util.GregorianCalendar;

/**
* Tests the interface QuizService and the class QuizServer.
*/
public class QuizServiceTest {

	//A QuizService object for use in the following tests.
	QuizService testService;
		
	/**
	 * Initialises a QuizService.
	 * Adds three uniqe players.
	 * Adds a quiz
	 */
	@Before
	public void buildUp() {
		try {
			testService = new QuizServer();
			testService.registerNewPlayer("Justinian");
			testService.registerNewPlayer("Valens");
			testService.registerNewPlayer("Theodora");
			//Creates a Quiz object to add to testService.
			String[] answers = new String[] {"Edirne", "Ankara", "Istanbul", "Athens"};
			List<Question> testList = new LinkedList<Question>();
			testList.add(new QuestionImpl("What is the modern name of Constantinople?", answers, 2));
			Quiz testQuiz = new QuizImpl(testList, "Theodora", "The Byzantine Quiz"); 
			//The quiz is assigned id 1.
			testService.addNewQuiz(testQuiz);
		} catch (Exception e) {}
	}
	
	@After
	public void cleanUp() {
		testService = null;
	}
	
	/**
	 * Tests userNameExists(String).
	 *
	 * Should return true because Valens exists already.
	 */
	@Test
	public void shouldReturnTrueBecauseValensExists() {
		try {
			assertTrue(testService.userNameExists("Valens"));
		} catch (Exception e) {}
	}

	/**
	 * Tests userNameExists(String).
	 *
	 * Should return false because Basil does not exist.
	 */
	@Test
	public void shouldReturnFalseBecauseBasilDoesntExist() {
		try {	
			assertFalse(testService.userNameExists("Basil"));
		} catch (Exception e) {}
	}

	/**
	 * Tests userNameExists(String).
	 *
	 * Should return false when userName is null.
	 */
	@Test
	public void shouldReturnFalseWhenUserNameIsNull() {
		try {
			assertFalse(testService.userNameExists(null));
		} catch (Exception e) {}
	}
	
	/**
	 * Tests registerNewPlayer(String).
	 *
	 * Should throw exception if userName is null.
	 */
	@Test (expected = NullPointerException.class)
	public void shouldThrowExceptionWhenUserNameIsNull() {
		try {
			testService.registerNewPlayer(null);
		} catch (RemoteException e) {}
	}

	/**
	 * Tests registerNewPlayer(String).
	 *
	 * Should throw exception if userName already exists.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenUserExists() {
		try {
			testService.registerNewPlayer("Valens");
		} catch (RemoteException e) {}
	}
	
	/**
	 * Tests addNewQuiz(Quiz).
	 *
	 * Should throw exception if the argument is null.
	 */
	@Test (expected = NullPointerException.class)
	public void shouldThrowExceptionIfQuizIsNull() {
		try {
			testService.addNewQuiz(null);
		} catch (RemoteException e) {}
	}
	
	/**
	 * Tests addNewQuiz(Quiz).
	 *
	 * Should throw exception if the author is not a registered Player.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfPlayerIsNotRegistered() {
		try {
			//Creates a Quiz object to add to testService.
			String[] answers = new String[] {"Byzantines", "Bulgars", "Avars", "Huns"};
			List<Question> testList = new LinkedList<Question>();
			testList.add(new QuestionImpl("Who built the Basilica Cistern?", answers, 0));
			//The author Basil has not been registered.
			Quiz testQuiz = new QuizImpl(testList, "Basil", "The Byzantine Quiz 2"); 
			testService.addNewQuiz(testQuiz);
		} catch (RemoteException e) {}
	}

	/**
	 * Tests addNewQuiz(Quiz).
	 *
	 * Should return quiz id 2..
	 */
	@Test
	public void shouldReturnQuizId2() {
		try {
			//Creates a Quiz object to add to testService.
			String[] answers = new String[] {"Byzantines", "Bulgars", "Avars", "Huns"};
			List<Question> testList = new LinkedList<Question>();
			testList.add(new QuestionImpl("Who built the Basilica Cistern?", answers, 0));
			Quiz testQuiz = new QuizImpl(testList, "Theodora", "The Byzantine Quiz 2"); 
			//Should assign testQuiz a quizId of 2.
			int actual = testService.addNewQuiz(testQuiz);
			assertEquals(2, actual);
		} catch (Exception e) {}
	}
	
	/**
	 * Tests terminateQuiz().
	 *
	 * Should throw exception if quizId doesn't exist.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfQuizIdDoesntExist() {
		try {
			//10 is not a regiestered quizId.
			testService.terminateQuiz("Valens", 10);
		} catch (RemoteException e) {}
	}
	
	/**
	 * Tests terminateQuiz().
	 *
	 * Should throw exception if userName doesn't exist.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfUserNameDidNotAuthorQuiz() {
		try {
			//Valens is not the author of the quizId 1.
			testService.terminateQuiz("Valens", 1);
		} catch (RemoteException e) {}
	}
	
	/**
	 * Tests terminateQuiz().
	 *
	 * Should throw exception if Quiz has already been terminated.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfQuizHasBeenTerminated() {
		try {
			//Terminates quizId 1.
			testService.terminateQuiz("Theodora", 1);
			//As the quiz has  already been terminated, an exception should now been thrown.
			testService.terminateQuiz("Theodora", 1);
		} catch (RemoteException e) {}
	}
	
	/**
	 * Tests getAllActiveQuizzes().
	 *
	 * There is only 1 active quiz.
	 * Therefore, the size of the quiz should be 1.
	 */
	@Test
	public void shouldReturnAQuizListOfSize1() {
		try {
			List<Quiz> temp = testService.getAllActiveQuizzes();
			assertTrue(temp.size() == 1);
		} catch (Exception e) {}
	}
	
	/**
	 * Tests getAllActiveQuizzes().
	 *
	 * Should return an empty list when all active quizzes are terminated.
	 */
	@Test
	public void shouldReturnEmptyListWhenQuizzesHaveBeenTerminated() {
		try {
			testService.terminateQuiz("Theodora", 1);
			List<Quiz> temp = testService.getAllActiveQuizzes();
			assertTrue(temp.isEmpty());
		} catch (Exception e) {}
	}
	
	/**
	 * Tests quizIdExists(int).
	 *
	 * Should return true because quizId 1 exists.
	 */
	@Test
	public void shouldReturnTrueBecauseQuiz1Exists() {
		try {
			assertTrue(testService.quizIdExists(1));
		} catch (Exception e) {}
	}

	/**
	 * Tests quizIdExists(int).
	 *
	 * Should return false because quizId 2 does not exist.
	 */
	@Test
	public void shouldReturnFalseBecauseQuiz2DoesntExist() {
		try {	
			assertFalse(testService.quizIdExists(2));
		} catch (Exception e) {}
	}

	/**
	 * Tests quizIdExists(int).
	 *
	 * Should throw exception when quizId is negative.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenQuizIdIsNegative() {
		try {	
			testService.quizIdExists(-2);
		} catch (RemoteException e) {}
	}
	
	/**
	 * Tests quizIdExists(int).
	 *
	 * Should throw exception when quizId is zero.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenQuizIdIsZero() {
		try {	
			testService.quizIdExists(0);
		} catch (RemoteException e) {}
	}
	
	/**
	 * Tests playQuiz().
	 *
	 * Should throw exception because quizId 4 doesnt exist.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowExceptionBecauseQuizId4DoesntExist() {
		try {
			testService.playQuiz("Theodora", 4);
		} catch (RemoteException e) {}
	}
	
	/**
	 * Tests playQuiz().
	 *
	 * Should throw exception because userName "Zoe" doesnt exist.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowExceptionBecausePlayerZoeDoesntExist() {
		try {
			testService.playQuiz("Zoe", 1);
		} catch (RemoteException e) {}
	}
	
	/**
	 * Tests playQuiz().
	 *
	 * Verifies the correct List of Question is returned.
	 * The list should be of size 1.
	 */
	@Test
	public void shouldReturnListOfQuestionsOfSize1() {
		try {
			List<Question> test = testService.playQuiz("Theodora", 1);
			assertEquals(1, test.size());
		} catch (Exception e) {}
	}
	
	/**
	 * Tests addNewAttempt().
	 *
	 * Should throw eexception because quizId 6 does not exist.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowExceptionBecauseQuiz6DoesntExist() {
		try {
			testService.addNewAttempt("Theodora", 1, new GregorianCalendar(), 6);
		} catch (RemoteException e) {}
	}
	
	/**
	 * Tests getWinner().
	 *
	 * Should throw exception when quizId does not exist.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowExceptionBecauseQuiz12DoesntExist() {
		try {
			testService.getWinner(12);
		} catch (RemoteException e) {}
	}
	
	/**
	 * Tests getWinner().
	 *
	 * Should returna list of size 1.
	 */
	@Test 
	public void shouldReturnWinnerTheodoraScore8() {
		Attempt actual = null;
		try {
			testService.addNewAttempt("Theodora", 8, new GregorianCalendar(), 1);
			testService.addNewAttempt("Valens", 2, new GregorianCalendar(), 1);
			actual = testService.getWinner(1);
		} catch (RemoteException e) {}
		assertEquals(8, actual.getScore());
		assertEquals("Theodora", actual.getUserName());
	}
}
