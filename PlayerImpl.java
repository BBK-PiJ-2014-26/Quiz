import java.io.Serializable;

/**
 * Implements the interface Player.
 * @author Gareth Moore
 */
public class PlayerImpl implements Player, Serializable {
	/**
	 * The Player's userName which must be unique.
	 */
	String userName;
	
	/**
	 * @param userName must be unique.
	 */
	public PlayerImpl(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
}
