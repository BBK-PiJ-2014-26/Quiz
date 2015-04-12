import java.rmi.Remote;
import java.rmi.RemoteException;

/**
* A service to allow users to either setup new quizzes or play existing quizzes.
*/
public interface QuizService extends Remote implements Serializable {

	/**
	 * Sends a list of quizzes to a Player client so that a Player can choose a quix to play.
	 *
	 * @return a list of all active quizzes.
	 * An active quiz is one which has not exceeded its expiryDate.
	 */
	List<Quiz> getAllActiveQuizzes() throws RemoteException;
	
	/**
	 * Allows a Player client to select a quiz to play.
	 *
	 * @param userName is Player's unique id.
	 * @param quizId of the quiz a Player wishes to play.
	 */
	void playQuiz(String userName, int quizId) throws RemoteException;
	
	/**
	 * Registers a new Player on the QuizService.
	 * 
	 * @param userName is the userName submitted by the user.
	 * userName must be unique.
	 * @throws IllegalArgumentException if the userName is not unique.
	 * @throws NullPointerException if the userName is null.
	 */
	void registerNewPlayer() throws RemoteException, IllegalArgumentException, NullPointerException;
	
	/**
	 * @param userName submitted by the Player client.
	 * @return true if the userName has not been registered.
	 */
	boolean isUnique(String userName) throws RemoteException;
}
