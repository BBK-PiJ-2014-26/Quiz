import java.util.TreeSet;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.LinkedList;

/**
 * Implements the interface QuizService.
 */
public class QuizServer extends UnicastRemoteObject implements QuizService {
	/**
	 * A TreeSet which holds registered Players on the QuizService.
	 * Uses a TreeSet because userNames are supplied randomly so the TreeSet should provide efficient searching.
	 */
	private TreeSet<Player> players;
	/**
	 * A LinkedList which holds Quizzes.
	 */
	private LinkedList<Quiz> quizzes;
	/**
	 * An int counter for tracking quizIds.
	 * Holds the next unique id. Each call of nextId() increments the counter.
	 */
	private int quizIdCounter;
	 
	
	public QuizServer() throws RemoteException {
		players = new TreeSet<Player>(new PlayerComparator());
		quizzes = new LinkedList<Quiz>();
		//Initialises the unique Id counter to 0.
		quizIdCounter = 1;
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
	
	public List<Quiz> getAllActiveQuizzes() throws RemoteException {
		return new LinkedList<Quiz>();
	}
	
	public List<Question> playQuiz(String userName, int quizId) throws RemoteException {
		return new LinkedList<Question>();
	}
	
	public void addNewAttempt(int quizId, String userName, int score) 
		throws RemoteException, IllegalArgumentException {}
		
	public int addNewQuiz(Quiz quiz) 
		throws RemoteException, IllegalArgumentException, NullPointerException {
			//Checks if quiz is null.
			//If true, an exception is thrown.
			if (quiz.equals(null)) {
				throw new NullPointerException();
			//Checks if the author is a registered Player.
			//If false, an exception is thrown.
			} else if (!userNameExists(quiz.getAuthor())) {
				throw new IllegalArgumentException();
			} else {
				//Calls nextId() to return the next unique quizId.
				int quizId = nextId();
				quiz.setQuizId(quizId);
				quizzes.add(quiz);
				return quizId;
			}
	}
	
	/**
	 * A method which controls the quizIdCounter.
	 * Returns the current value of quizIdCounter,
	 * then increments the counter by 1.
	 * Has private access because the method should only be accessed within addNewQuiz(Quiz).
	 *
	 * @return a unique quizId.
	 */
	private int nextId() {
		int result = quizIdCounter;
		quizIdCounter++;
		return result;
	}
	
	public void terminateQuiz(String userName, int quizId) 
		throws RemoteException, IllegalArgumentException {}
		
	//private void flush() {}
	
	//private void read() {}
}
