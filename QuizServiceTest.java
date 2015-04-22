import java.rmi.Remote;
import java.rmi.RemoteException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
* Tests the interface QuizService.
*/
public class QuizServiceTest {

		//A QuizService object for use in the following tests.
		QuizService testService;
		
		/**
		 * Initialises a QuizService.
		 * Adds three uniqe players.
		 */
		@Before
		public void buildUp() {
			testService = new QuizServer();
			testService.registerNewPlayer("Justinian");
			testService.registerNewPlayer("Valens");
			testService.registerNewPlayer("Theodora");
		}
}
