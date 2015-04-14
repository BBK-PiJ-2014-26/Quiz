/**
 * Implements the interface Player.
 */
public class PlayerImpl implements Player {
	/**
	 * The Player's userName which must be unique.
	 */
	String userName;

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
}
