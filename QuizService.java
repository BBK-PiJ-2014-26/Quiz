import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Calendar;

/**
 * A service to allow users to either setup new quizzes or play existing quizzes.
 * @author Gareth Moore
 */
public interface QuizService extends Remote {

	//Methods below are designed for use on either the Setup Client or the Player Client
	
	/**
	 * Registers a new Player on the QuizService.
	 * 
	 * @param userName is the userName submitted by the user.
	 * userName must be unique.
	 * @throws IllegalArgumentException if the userName already exists.
	 * @throws NullPointerException if the userName is null.
	 */
	void registerNewPlayer(String userName) throws RemoteException, IllegalArgumentException, NullPointerException;
	
	/**
	 * Takes a userName submitted by a Client and confirms that it is registered on the QuizService.
	 * If the userName argument is null, false is returned.
	 *
	 * @param userName submitted by the Player client.
	 * @return true if the userName has been registered.
	 */
	boolean userNameExists(String userName) throws RemoteException;
	
	/**
	 * Takes a quizId and confirms that it is registered on the QuizService.
	 *
	 * @param quizId to be checked.
	 * @return true if the quizId exists.
	 * @throws IllegalArgumentException if quizId is 0 or negative.
	 */
	public boolean quizIdExists(int quizId) throws RemoteException, IllegalArgumentException;
	
	//The following methods are designed for use by the Player Client.
	
	/**
	 * Sends a list of quizzes to a Player client so that a Player can choose a quiz to play.
	 *
	 * @return a list of all active quizzes.
	 * An active quiz is one which has not been terminated.
	 */
	List<Quiz> getAllActiveQuizzes() throws RemoteException;
	
	/**
	 * Allows a Player client to select a quiz to play.
	 *
	 * @param userName is Player's unique id.
	 * @param quizId of the quiz a Player wishes to play.
	 * @return the list of questions for the Player's chosen quiz.
	 */
	List<Question> playQuiz(String userName, int quizId) throws RemoteException, IllegalArgumentException;
		
	/**
	 * Once a Player has complted a quiz, this method adds a new Attempt.
	 * Moreover, if the Player has the new highest score, 
	 * the quiz's leader field is updated.
	 *
	 * @param attempt at the quiz.
	 * @param quizId of the quiz attempted.
	 * @throws IllegalArgumentException if the quizId does not exist.
	 */
	void addNewAttempt(Attempt attempt, int quizId) throws RemoteException, IllegalArgumentException;	 
	
	
	//These methods are designed for use with the Setup Client.
	
	/**
	 * Registers a new quiz on the QuizService.
	 *
	 * @param quiz is the Quiz the user wishes to add to the QuizService.
	 * @return a unique quizId.
	 * @throws IllegalArgumentException if the author is not a registered Player.
	 * @throws NullPointerException if quiz is null.
	 */
	int addNewQuiz(Quiz quiz) throws RemoteException, IllegalArgumentException, NullPointerException;
	
	/**
	 * Terminates an active quiz. The quiz must be terminated by the author.
	 *
	 * @param userName of the Player terminating the quiz.
	 * @param quizId of the quiz to be terminated.
	 * @throws IllegalArgumentException if the quizId does not exist or if the quiz has already been terminated.
	 * @throws IllegalArgumentException if the userName does not macth the author of the quiz.
	 */
	void terminateQuiz(String userName, int quizId) throws RemoteException, IllegalArgumentException;
	
	/**
	 * Returns the top 3 scores in a given quiz.
	 *
	 * @param quizId of the quiz.
	 * @throws IllegalArgumentException if the quizId does not exist.
	 * @return the winning Attempt.
	 */
	Attempt getWinner(int quizId) throws RemoteException, IllegalArgumentException;

	/**
	 * Returns the Top 3 of a requested quiz. 
	 * If there are less than 3 Attempts, a Leaderboard of appropriate size is returned.
	 *
	 * @param quizId of the requested Quiz.
	 * @throws IllegalArgumentException if the quizId does not exist.
	 * @return Leaderboad containing top 3 scores.
	 */
	Leaderboard getTop3(int quizId) throws RemoteException, IllegalArgumentException;
}
