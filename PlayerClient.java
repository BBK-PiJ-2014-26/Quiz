/** 
 * A client which allows users to select and play a Quiz.
 */
public interface PlayerClient {
	
	/**
	 * An initial menu which offers two options, 
	 * login and register new user.
	 */
	void welcome();
	
	/**
	 * Once a user has logged in or registered,
	 * they are given a list of quizzes to select from.
	 */
	void selectQuiz();
	
	/**
	 * Handles the inerface while a user plays a quiz.
	 * 
	 * @param questions are the question from the quiz a Player has selected.
	 */
	void playQuiz(List<Question> questions);
}
