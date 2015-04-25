/**
 * A programme which allows a user to conect to a QuizService.
 */
public interface QuizClient {

	/**
	 * Launches tyhe QuizClient and connects to the QuizService.
	 *
	 * @param url of the QuizService.
	 */
	void launch(String url);
	
	/**
	 * Takes a userName from the Player.
	 * Allows the Player to proceed if the userName is registered on the QuizService.
	 *
	 * @param userName submitted by the player.
	 * @return true if the Player's userName is registered.
	 */
	boolean login(String userName);
	
	/**
	 * Take a userName from the Player and registers it on the QuizService.
	 * The userName must be unique.
	 *
	 * @param userName the Player wishes to register.
	 * @return true if userName is unique and the registration is successfull.
	 */
	boolean register(String userName);
}
