import java.util.List;

/** 
 * A client which allows users to select and play a Quiz.
 */
public interface PlayerClient extends QuizClient {
	
	/**
	 * Once a user has logged in or registered,
	 * they are given a list of quizzes to select from.
	 *
	 * @param userName of the Player.
	 */
	void selectQuiz(String userName);
	
	/**
	 * Handles the inerface while a user plays a quiz.
	 * 
	 * @param questions are the question from the quiz a Player has selected.
	 * @param userName of the Player.
	 * @param quizId of the Quiz to be played.
	 */
	void playQuiz(List<Question> questions, String userName, int quizId);
}
