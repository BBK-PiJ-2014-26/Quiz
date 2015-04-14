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
	String getUserName();
}
