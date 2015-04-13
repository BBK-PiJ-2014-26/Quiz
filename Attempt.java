import java.util.Date;

/**
 * An Attempt object is a record of a user's attempt at a particular quiz.
 *
 * QuizIds are not explicity recorded because the identity of the Quiz is implicit.
 * Attempts are recorded within a List which is an instance variable of each Quiz object.
 */
public interface Attempt {
	
	/**
	 * @return the userName of the Player who attempted this quiz.
	 */
	String getUserName();
	
	
	/**
	 * @return the score recorded for this attempt.
	 */
	double getScore();
	
	/**
	 * @return the date of the attempt.
	 */
	Date getDate();
}
