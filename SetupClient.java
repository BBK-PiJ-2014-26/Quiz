/**
 * A client which enables a user to setup new quizzes on a QuizService.
 *
 * In addition, a user can terminate an active quiz and register as a new user.
 */
public interface SetupClient {
	
	/**
	 * An initial menu which offers two options, 
	 * login and register new user.
	 */
	void welcome();
	
	/**
	 * Once a user has logged in or registered, they are prompted to choose an option.
	 * Available options are Setup New Quiz or Terminate Quiz.
	 */
	void chooseOption();
	
	/**
	 * If a user selects Setup New Quiz, this method will collect the Quiz data.
	 *
	 * @return the quizId for the new Quiz.
	 */
	int setupNewQuiz();
	
	/**
	 * If a user selects Terminate, this method terminate the quiz 
	 * and return quiz details to the user.
	 *
	 * @param quizId of the QuizId the Player wishes to terminate.
	 */
	void terminateQuiz(int quizId);
	
	/**
	 * Takes a userName from the Player.
	 * Allows the Player to proceed if the userName is registered on the QuizService.
	 *
	 * @param userName submitted by the player.
	 * @return true if the Player's userName is registered.
	 */
	boolean login(String userName);
	
	/**
	 * Take a userName from the Player and registers it on the QuizService.
	 * The userName must be unique.
	 *
	 * @param userName the Player wishes to register.
	 * @return true if userName is unique and the registration is successfull.
	 */
	boolean register(String userName);
}
