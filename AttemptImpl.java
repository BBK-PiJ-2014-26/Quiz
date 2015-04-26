import java.util.Calendar;
import java.io.Serializable;

/**
 * Implements the interface Attempt.
 */
public class AttemptImpl implements Attempt, Serializable {
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
	 
	public AttemptImpl(String userName, int score) {
	 	 this.userName = userName;
	 	 this.score = score;
	 	 this.date = Calendar.getInstance();
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
