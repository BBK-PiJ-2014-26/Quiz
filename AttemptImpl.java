import java.util.Calendar;

/**
 * Implements the interface Attempt.
 */
public class AttemptImpl implements Attempt {
	/**
	 * The userName of the Player who attempted the quiz.
	 */
	String userName;
	/**
	 * The score of the player who attempted the quiz.
	 */
	int score;
	/**
	 * The date when a Player attempted the quiz.
	 */
	Calendar date;
	 
	public AttemptImpl(String userName, int score, Calendar date) {
	 	 this.userName = userName;
	 	 this.score = score;
	 	 this.date = date;
	}
	 
	public String getUserName() {
		return userName;
	}
	
	public int getScore() {
		return score;
	}
	
	public Calendar getDate() {
		return date;
	}
}
