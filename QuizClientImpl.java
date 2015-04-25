import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

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
		//No action required for RemoteException,
		//as result is initialised to false.
		} catch (RemoteException e) {}
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
		} catch (Exception e) {}
		//If there is an exception, no action is required,
		//because result has been initialised to false.
		return result;
	}
}
