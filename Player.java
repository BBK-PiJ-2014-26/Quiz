/**
 * A Player object uniquely identifies each registered user.
 *
 * Every attempt at a quiz a Player makes is recorded here.
 */
public interface Player {
	
	/**
	 * Sets a unique for each instance of Player.
	 *
	 * @param playerId is a unique number provided by the QuizServer.
	 * Ids are provided by the server to ensure uniqueness.
	 */
	void setPlayerId(int playerId);
	
	/**
	 * @return the Player's playerId.
	 */
	int getId();
	
	/**
	 * Adds a new attempt to the Player's history of attempts.
	 *
	 * @param quizId is the quiz attempted by the player.
	 * @param score that the Player scored on this attempt.
	 * @param date which the Player attempted this quiz.
	 */
	void addNewAttempt(int quizId, double score, Date date);
	
	/**
	 * @return the full history of attempts made by this Player.
	 */
	List<Attempt> getAllAttempts();
}
