import java.util.Calendar;
import java.util.List;

/**
 * A Quiz object refers to each quiz created by a Player.
 *
 * It has a unique ID, a list of Questions, a name, and an author.
 * A quiz has a boolean flag to indicate if the quiz has terminated or not.
 * A leaderoard stores the scores in ascending order.
 */
public interface Quiz {

	/**
	 * Sets the unique id for each Quiz.
	 *
	 * @param quizId unique id provided by the QuizServer.
	 * The server provides the id to ensure uniqueness.
	 */
	void setQuizId(int quizId);
	
	/**
	 * Sets the status of the quiz to terminated.
	 */
	void terminate();
	
	/**
	 * @return true if the quiz has been terminated.
	 */
	boolean isTerminated();
	
	/**
	 * Adds a new Question to the quiz.
	 *
	 * @param question is the question itself.
	 * @param possibleAnswers the choice of answers for this Question.
	 * @param correctAnswer the index of the correct answer from possibleAnswers.
	 * @throws NullPointerException if question or possibleAnswers is null.
	 * @throws IllegalArgumentException if correctAnswer is less than zero or greater than 3.
	 * @throws IllegalArgumentException if possibleAnswers is of a size greater than 4.
	 */
	void addNewQuestion(String question, String[] possibleAnswers, int correctAnswer) 
		throws NullPointerException, IllegalArgumentException;
	
	/**
	 * @return the author of the quiz.
	 */
	String getAuthor();
	
	/**
	 * @return quizId.
	 */
	int getQuizId();
	
	/**
	 * @return name.
	 */
	String getName();
	
	/**
	 * @return list of questions.
	 */
	List<Question> getQuestions(); 

	/**
	 * Adds a new attempt to the Quiz's leaderboard.
	 *
	 * @param attempt at the quiz.
	 * @throws NullPointerException if attempt is null.
	 */
	void addNewAttempt(Attempt attempt) throws NullPointerException;
	
	/**
	 * @return the full Leaderboard of this quiz. 
	 */
	Leaderboard getLeaderboard();
}
