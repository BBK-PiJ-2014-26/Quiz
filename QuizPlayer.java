import java.rmi.RMISecurityManager;

/**
 * Contains the main method to run a PlayerClient.
 */
public class QuizPlayer {
	
	/**
	 *@param args should contain the url of the QuizService.
	 */
	public static void main(String[] args) {
		
		//Takes the url of the QuizService from the command line.
		String url = args[0];
		PlayerClient client = new PlayerClientImpl();
		client.launch(url);
		client.welcome();
	}
}
