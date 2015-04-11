import java.util.Date;

/**
 * An Attempt object is a record of a user's attempt at a particular quiz.
 *
 * PlayerIds are not explicity recorded because the identity of the Player is implicit.
 * Attempts are recorded within an ArrayList which is an instance variable of each Player object.
 */
public interface Attempt {
	
	/**
	 * Sets the Id of the quiz which was attempted.
	 */
	void setQuizId(int quizId);
	
	/**
	 * @return the QuizId for the quiz which was attempted.
	 */
	int getQuizId();
	
	/**
	 * Sets the score recorded for this attempt.
	 */
	void setScore(double score);
	
	/**
	 * @return the score recorded for this attempt.
	 */
	double getScore();
	
	/**
	 * Sets the date of this attempt.
	 *
	 * @param date when the Quiz was attempted. 
	 * Dates are provided by the server rather than client machine to ensure consistency.
	 */
	void setDate(Date date);
	
	/**
	 * @return the date of the attempt.
	 */
	Date getDate();
}
