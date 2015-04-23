import java.util.TreeSet;

/**
 * Implements the interface QuizService.
 */
public class QuizServer implements QuizService {
	/**
	 * A TreeSet which holds all of the registered Players on the QuizService.
	 * Uses a TreeSet because this enables quicker searching.
	 */
	private TreeSet<Player> players;
	
	public QuizServer() {
		this.players = TreeSet<Player>(new PlayerComparator());
	}

	public void registerNewPlayer(String userName) 
		throws RemoteException, IllegalArgumentException, NullPointerException {
			//Checks whether userName is null.
			//If true, an exception is thrown.
			if (userName.equals(null)) {
				throw new NullPointerException();
			//Checks whether userName has already been registered.
			//If true, an exception is thrown.
			} else if (userNameExists(userName)) {
				throw new IllegalArgumentException();
			} else {
				players.add(new PlayerImpl(userName));
			}
		}
	}
	
	public boolean userNameExists(String userName) {
		boolean result = false;
		//Creates a temporary Player object using the argument userName.
		Player temp = new PlayerImpl(userName);
		//Uses this temp Player to check if it's contained within the instance variable players.		
		if (players.contains(temp)) {
			result = true;
		}
		return result;
	}
		
}
