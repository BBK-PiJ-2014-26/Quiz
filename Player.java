import java.util.List;
import java.util.Date;

/**
 * A Player object uniquely identifies each registered user.
 *
 * Every attempt at a quiz a Player makes is recorded here.
 */
public interface Player {
	
	/**
	 * Sets a unique for each instance of Player.
	 *
	 * @param userName is a unique String chosen by the user.
	 * userName must be checked against the existing Players to ensure uniqueness.
	 */
	void setUserName(String userName);
	
	/**
	 * @return the Player's userName.
	 */
	int getUserName();
	
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
