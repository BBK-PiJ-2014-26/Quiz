/**
 * A client which enables a user to setup new quizzes on a QuizService.
 * Extends the interface QuizClient.
 *
 * In addition, a user can terminate an active quiz and register as a new user.
 */
public interface SetupClient extends QuizClient {
	
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
}
