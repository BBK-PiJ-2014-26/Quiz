import java.util.Date;

/**
 * An Attempt object is a record of a user's attempt at a particular quiz.
 *
 * Player's userNames are not explicity recorded because the identity of the Player is implicit.
 * Attempts are recorded within an ArrayList which is an instance variable of each Player object.
 */
public interface Attempt {
	
	/**
	 * @return the QuizId for the quiz which was attempted.
	 */
	int getQuizId();
	
	
	/**
	 * @return the score recorded for this attempt.
	 */
	double getScore();
	
	/**
	 * @return the date of the attempt.
	 */
	Date getDate();
}
