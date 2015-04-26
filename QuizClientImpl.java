import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.util.Scanner;

/**
 * Implements the interface QuizClient.
 */
public class QuizClientImpl implements QuizClient {
	/**
	 * The QuizService this Client connects to.
	 */
	QuizService service;
	
	public void launch(String url) {
		try {
			Remote remoteService = Naming.lookup(url + ":1099/quiz");
			//Downcasts Remote to a QuizService.
			service = (QuizService) remoteService;
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		} catch (NotBoundException ex) {
			ex.printStackTrace();
		}
	}
	
	public boolean login(String userName) {
		boolean result = false;
		//Checks whether the userName is registered on the QuizService.
		try {
			if (service.userNameExists(userName)) {
				result = true;
			}
		//No action required for Exception,
		//as result is initialised to false.
		} catch (Exception e) {}
		return result;
	}
	
	public boolean register(String userName) {
		boolean result = false;
		//Checks whether the userName exists on the QuizService.
		//If false, the userName is unique and the QuizService registers it.
		try {
			if (!service.userNameExists(userName)) {
				service.registerNewPlayer(userName);
				result = true;
			}
		//If there is an exception, no action is required,
		//because result has been initialised to false.
		} catch (Exception e) {}
		return result;
	}
	
	public void welcome() {
		System.out.println("\n\n~~~Welcome to Quizzes of the Ancients~~~");
		selectWelcomeOption();		
	}
	
	/**
	 * Prompts the user for a selection within welcome.
	 * Private because this method should only be used within the context of welcome().
	 */
	private void selectWelcomeOption() {
		System.out.print("\nPlease select an option.\n" + "\n");
		System.out.print("Type 1 to login.\n" + "Type 2 to register new player.\n" + "Type 3 to exit\n"+ "Selection: ");
		Scanner sc = new Scanner(System.in);
		String selection = sc.next();
		//Checks if the user has selected 1.
		//If true, runs login().
		if (selection.equals("1")) {
			String userName = requestUserName();
			//Attempts to login the user.
			//If login succeeds, the user proceeds.			
			if (login(userName)) {
				chooseOption(userName);
			//If login fails, selectWelcomeOption is called again.
			} else {
				System.out.println("Username does not exist. Please try again.");
				selectWelcomeOption();
			}
		//Checks if the user has selected 2.
		//If true, runs register().
		} else if (selection.equals("2")) {
			String userName = requestUserName();
			//Attempts to register a new Player.
			//If registration fails, calls selectWelcomeOption().
			if (!register(userName)) {
				System.out.println("Username is taken. Please try again.");
				selectWelcomeOption();
			//If registration succeeds, the user proceeds.
			} else {
				chooseOption(userName);
			}
		//Checks if user has selected 3.
		//If true, the programme will terminate.
		} else if (selection.equals("3")) {
		//If none of these conditions are true, an error has occurred.
		} else {
			selectWelcomeOption();
		}
	}
	
	public void chooseOption(String userName) {
	}
	
	/**
	 * Request a user userName from the user.
	 *
	 * @return the userName submitted by the user.
	 */
	private String requestUserName() {
		System.out.print("Please enter your username: ");
		Scanner sc = new Scanner(System.in);
		String userName = sc.next();
		return userName;
	}
}
