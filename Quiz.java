import java.util.Date;

/**
 * A Quiz object refers to each quiz created by a Player.
 *
 * It has a unique ID, a list of Questions, a name, and an expiry date.
 * It also records either the winner if the quiz has expired 
 * or the current leader if it has not.
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
	 * Sets the name of the quiz. Name does not need to be unique.
	 *
	 * @param name of the quiz.
	 */
	void setName();
	
	/**
	 * Sets the date when the Quiz will expire.
	 *
	 * @param expiryDate of the Quiz.
	 * @throws IllegalArgumentException if expiryDate is in the past.
	 * @throws NullPointerExcpetion if expiryDate is null.
	 */
	void setExpiryDate(Date expiryDate) throws IllegalArgumentException, NullPointerException;
	
	/**
	 * Sets the playerId of the winning Player.
	 * If the expiryDate has expired, this is the eventual Winner.
	 * But if the expiryDate is in the future, this is the current leader.
	 *
	 * @param playerId of the winning Player.
	 * @throws IllegalArgumentException if the Player does not exist.
	 */
	void setWinningPlayer(int playerId) throws IllegalArgumentException;
	
	/**
	 * Adds a new Question to the quiz.
	 *
	 * @param question is the question itself.
	 * @param possibleAnswers the choice of answers for this Question.
	 * @param correctAnswer the index of the correct answer from possibleAnswers.
	 */
	void addNewQuestion(String question, String[] possibleAnswers, int correctAnswer);
}
