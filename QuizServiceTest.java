import java.rmi.Remote;
import java.rmi.RemoteException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

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
		testService = new QuizServer();
		testService.registerNewPlayer("Justinian");
		testService.registerNewPlayer("Valens");
		testService.registerNewPlayer("Theodora");
		//Creates a Quiz object to add to testService.
		String[] answers = new String[] {"Edirne", "Ankara", "Istanbul", "Athens"};
		List<Question> testList = LinkedList<Question>();
		testList.add(new QuestionImpl("What is the modern name of Constantinople?", answers, 2));
		Quiz testQuiz = new QuizImpl(testList, "Theodora", "The Byzantine Quiz"); 
		//The quiz is assigned id 1.
		addNewQuiz(testQuiz);
	}
	
	/**
	 * Tests userNameExists(String).
	 *
	 * Should return true because Valens exists already.
	 */
	@Test
	public void shouldReturnTrueBecauseValensExists() {
		assertTrue(testService.userNameExists("Valens"));
	}

	/**
	 * Tests userNameExists(String).
	 *
	 * Should return false because Basil does not exist.
	 */
	@Test
	public void shouldReturnFalseBecauseBasilDoesntExist() {
		assertFalse(testService.userNameExists("Basil"));
	}

	/**
	 * Tests userNameExists(String).
	 *
	 * Should return false when userName is null.
	 */
	@Test
	public void shouldReturnFalseWhenUserNameIsNull() {
		assertFalse(testService.userNameExists(null));
	}
	
	/**
	 * Tests registerNewPlayer(String).
	 *
	 * Should throw exception if userName is null.
	 */
	@Test (expected = NullPointerException.class)
	public void shouldThrowExceptionWhenUserNameIsNull() {
		testService.registerNewPlayer(null);
	}

	/**
	 * Tests registerNewPlayer(String).
	 *
	 * Should throw exception if userName already exists.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenUserExists() {
		testService.registerNewPlayer("Valens");
	}
	
	/**
	 * Tests addNewQuiz(Quiz).
	 *
	 * Should throw exception if the argument is null.
	 */
	@Test (expected = NullPointerException.class)
	public void shouldThrowExceptionIfQuizIsNull() {
		testService.addNewQuiz(null);
	}
	
	/**
	 * Tests addNewQuiz(Quiz).
	 *
	 * Should throw exception if the author is not a registered Player.
	 */
	@Test (expected = NullPointerException.class)
	public void shouldThrowExceptionIfQuizIsNull() {
		//Creates a Quiz object to add to testService.
		String[] answers = new String[] {"Byzantines", "Bulgars", "Avars", "Huns"};
		List<Question> testList = LinkedList<Question>();
		testList.add(new QuestionImpl("Who built the Basilica Cistern?", answers, 0));
		//The author Basil has not been registered.
		Quiz testQuiz = new QuizImpl(testList, "Basil", "The Byzantine Quiz 2"); 
		addNewQuiz(testQuiz);
	}

	/**
	 * Tests addNewQuiz(Quiz).
	 *
	 * Should return quiz id 2..
	 */
	@Test (expected = NullPointerException.class)
	public void shouldThrowExceptionIfQuizIsNull() {
		//Creates a Quiz object to add to testService.
		String[] answers = new String[] {"Byzantines", "Bulgars", "Avars", "Huns"};
		List<Question> testList = LinkedList<Question>();
		testList.add(new QuestionImpl("Who built the Basilica Cistern?", answers, 0));
		Quiz testQuiz = new QuizImpl(testList, "Theodora", "The Byzantine Quiz 2"); 
		//Should assign testQuiz a quizId of 2.
		int actual = addNewQuiz(testQuiz);
		assertEquals(2, actual);
	}
	
	/**
	 * Tests terminateQuiz().
	 *
	 * Should throw exception if quizId doesn't exist.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfQuizIdDoesntExist() {
		//10 is not a regiestered quizId.
		testService.terminateQuiz("Valens", 10);
	}
	
	/**
	 * Tests terminateQuiz().
	 *
	 * Should throw exception if userName doesn't exist.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfUserNameDidNotAuthorQuiz() {
		//Valens is not the author of the quizId 1.
		testService.terminateQuiz("Valens", 1);
	}
	
	/**
	 * Tests terminateQuiz().
	 *
	 * Should throw exception if Quiz has already been terminated.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfQuizHasBeenTerminated() {
		//Terminates quizId 1.
		testService.terminateQuiz("Theodora", 1);
		//As the quiz has  already been terminated, an exception should now been thrown.
		testService.terminateQuiz("Theodora", 1);
	}
}
