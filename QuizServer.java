import java.util.TreeSet;

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
	}
		
}
