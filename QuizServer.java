import java.util.TreeSet;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.LinkedList;
import java.util.Calendar;
import java.util.ListIterator;

/**
 * Implements the interface QuizService.
 */
public class QuizServer extends UnicastRemoteObject implements QuizService {
	/**
	 * A TreeSet which holds registered Players on the QuizService.
	 * Uses a TreeSet because userNames are supplied randomly
	 * The TreeSet should provide efficient searching.
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
		List<Quiz> result = new LinkedList<Quiz>();
		ListIterator<Quiz> iterator = quizzes.listIterator();
		while (iterator.hasNext()) {
			Quiz temp = iterator.next();
			//Checks whether the current Quiz has been terminated.
			if (!temp.isTerminated()) {
				result.add(temp);
			}
		}
		return result;
	}
	
	public List<Question> playQuiz(String userName, int quizId) 
		throws RemoteException, IllegalArgumentException {
			//Checks that userName and quizId exist on this QuizService.
			//If either is false, an exception is thrown.
			if (!userNameExists(userName) || !quizIdExists(quizId)) {
				throw new IllegalArgumentException();
			} else {
				Quiz temp = getQuiz(quizId);
				List<Question> result = temp.getQuestions();
				return result;
			}
	}
	
	public void addNewAttempt(String userName, int score, Calendar date, int quizId)
		throws RemoteException, IllegalArgumentException {
			//Checks whether the quizId exists.
			//If false, an exception is thrown.
			if (!quizIdExists(quizId)) {
				throw new IllegalArgumentException();
			} else {
				//Creates an iterator to search the list.
				ListIterator<Quiz> iterator = quizzes.listIterator(0);
				boolean finished = false;
				while (!finished) {
					if (iterator.hasNext()) {
						Quiz temp = iterator.next();
						if (temp.getQuizId() == quizId) {
							iterator.remove();
							temp.addNewAttempt(userName, score, date);
							quizzes.add(temp);
							finished = true;
						}
					} else {
						finished = true;
					}
				}
			}
	}
		
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
		throws RemoteException, IllegalArgumentException {
			//Checks whether quizId exists.
			//If false, an exception is thrown
			if (!quizIdExists(quizId)) {
				throw new IllegalArgumentException();
			} else {
				//Creates an iterator to search the list.
				ListIterator<Quiz> iterator = quizzes.listIterator(0);
				boolean finished = false;
				while (!finished) {
					if (iterator.hasNext()) {
						Quiz temp = iterator.next();
						if (temp.getQuizId() == quizId) {
							//Checks whether userName matches temp's author.
							//If false, an exception is thrown.
							if (!userName.equals(temp.getAuthor())) {
								throw new IllegalArgumentException();
							}
							//Checks whether this Quiz has already been terminated.
							//If true, an exception is thrown.
							if (temp.isTerminated()) {
								throw new IllegalArgumentException();
							}
							iterator.remove();
							temp.terminate();
							quizzes.add(temp);
							finished = true;
						}
					} else {
						finished = true;
					}
				}
			}
	}
	
	/**
	 * A method which retreives a Quiz object from quizzes.
	 * Has private access because a client does need to retreive a quiz.
	 *
	 * @param quizId is the unique id which identifies the desired Quiz.
	 * @return the desired Quiz.
	 */
	private Quiz getQuiz(int quizId) {
		Quiz result = null;
		//Creates an iterator to search the list.
		ListIterator<Quiz> iterator = quizzes.listIterator();
		boolean finished = false;
		while (!finished) {
			if (iterator.hasNext()) {
				Quiz temp = iterator.next();
				if (temp.getQuizId() == quizId) {
					result = temp;
					finished = true;
				}
			} else {
				finished = true;
			}
		}
		return result;
	}
	
	public boolean quizIdExists(int quizId) throws RemoteException, IllegalArgumentException {
		//Check whether quizId is 0 or negative.
		//If true, an exception is thrown.
		if (quizId <= 0) {
			throw new IllegalArgumentException();
		} else {
			boolean result = false;
			//Creates an iterator to search the list.
			ListIterator<Quiz> iterator = quizzes.listIterator(0);
			boolean finished = false;
			while (!finished) {
				if (iterator.hasNext()) {
					Quiz temp = iterator.next();
					if (temp.getQuizId() == quizId) {
						result = true;
						finished = true;
					}
				} else {
					finished = true;
				}
			}
			return result;
		}
	}
	
	public Attempt getWinner(int quizId) throws RemoteException, IllegalArgumentException {
		//Check whether quizId exists.
		//If false, an exception is thrown.
		if (!quizIdExists(quizId)) {
			throw new IllegalArgumentException();
		} else {
			Quiz temp = getQuiz(quizId);
			Leaderboard tempLeaderboard = temp.getLeaderboard();
			Attempt winner = tempLeaderboard.get(0);
			return winner;
		}
	}
		
	//private void flush() {}
	
	//private void read() {}
}
