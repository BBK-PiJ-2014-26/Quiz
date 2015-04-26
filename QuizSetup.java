/**
 * Contains the main method to run a SetupClient.
 * @author Gareth Moore
 */
public class QuizSetup {
	
	/**
	 *@param args should contain the url of the QuizService.
	 */
	public static void main(String[] args) {
		//Takes the url of the QuizService from the command line.
		String url = args[0];
		SetupClient client = new SetupClientImpl();
		client.launch(url);
		client.welcome();
	}
}
