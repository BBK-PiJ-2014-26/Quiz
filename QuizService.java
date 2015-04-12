import java.rmi.Remote;
import java.rmi.RemoteException;

/**
* A service to allow users to either setup new quizzes or play existing quizzes.
*/
public interface QuizService extends Remote implements Serializable {

	//Methods below are designed for use on either the Setup Client or the Player Client
	
	/**
	 * Registers a new Player on the QuizService.
	 * 
	 * @param userName is the userName submitted by the user.
	 * userName must be unique.
	 * @throws IllegalArgumentException if the userName is not unique.
	 * @throws NullPointerException if the userName is null.
	 */
	void registerNewPlayer(String userName) throws RemoteException, IllegalArgumentException, NullPointerException;
	
	/**
	 * Takes a userName submitted by a Client and confirms that it is registered on the QuizService.
	 *
	 * @return true if the userName is registered.
	 */
	boolean userNameExist() throws RemoteException;
	
	/**
	 * @param userName submitted by the Player client.
	 * @return true if the userName has not been registered.
	 * @throws NullPointerException if the userName is null.
	 */
	boolean isUnique(String userName) throws RemoteException, NullPointerException;
	
	
	//The following methods are designed for use by the Player Client.
	
	/**
	 * Sends a list of quizzes to a Player client so that a Player can choose a quiz to play.
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
	 * @return the list of questions for the PLayer's chosen quiz.
	 */
	List<Question> playQuiz(String userName, int quizId) throws RemoteException;
		
	/**
	 * Once a Player has complted a quiz, this method adds a new Attempt.
	 * Moreover, if the Player has the new highest score, 
	 * the quiz's leader field is updated.
	 *
	 * @param quizId of the quiz the Player has completed.
	 * @param userName uniquely identifies the Player.
	 * @param score which the Player record for this quiz.
	 * @throws IllegalArgumentException if either quizId or userName does not exist.
	 */
	void addNewAttempt(int quizId, String userName, double score) throws RemoteException, IllegalArgumentException;	 
	
	
	//These methods are designed for use with the Setup Client.
	
	/**
	 * Registers a new quiz on the QuizService.
	 *
	 * @param questions is a list of questions submitted by the user.
	 * @param userName is the author of the quiz.
	 * @param name of the quiz.
	 * @return a unique quizId.
	 * @throws IllegalArgumentException if the userNam is not registered.
	 */
	int addNewQuiz(List<Question> questions, String userName, String name) throws RemoteException;
	
	/**
	 * Terminates an active quiz. The quiz must be terminated by the author.
	 *
	 * @param userName of the Player terminating the quiz.
	 * @param quizId of the quiz to be terminated.
	 * @throws IllegalArgumentException if the quizId does not exist or if the quiz has already been terminated.
	 * @throws IllegalArgumentException if the userName does not macth the author of the quiz.
	 */
	void terminateQuiz(String userName, int quizId) throws RemoteException, IllegalArgumentException;
	
	
	// These methods are not accessible to the PLay Client or Setup Client.
	
	/**
	 * Writes the contents of the QuizServer to disk.
	 *
	 */
	private void flush();
	
	/**
	 * Read the contents of the QuizServer from disk.
	 */
	private void read();	
}
