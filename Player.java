import java.util.List;
import java.util.Date;

/**
 * A Player object uniquely identifies each registered user.
 *
 * Every attempt at a quiz a Player makes is recorded here.
 * @author Gareth Moore
 */
public interface Player {
	
	/**
	 * @return the Player's userName.
	 */
	String getUserName();
}
