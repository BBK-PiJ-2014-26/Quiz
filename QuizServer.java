import java.util.TreeSet;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.LinkedList;
import java.util.Calendar;
import java.util.ListIterator;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.util.Comparator;
import java.io.Serializable;

/**
 * Implements the interface QuizService.
 * @author Gareth Moore
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
	private Integer quizIdCounter;
	
	private static final long serialVersionUID = 4222;
	 
	
	public QuizServer() throws RemoteException {
		super();
		players = new TreeSet<Player>(new PlayerComparator());
		quizzes = new LinkedList<Quiz>();
		//Initialises the unique Id counter to 1.
		quizIdCounter = 1;
		//Checks whether players.data, quizzes.data and quizIdCounter.data exist.
		//If true, calls read().
		File player = new File("./players.data");
		File quiz = new File("./quizzes.data");
		File quizCounter = new File("./quizIdCounter.data");
		if (player.exists() && quiz.exists() && quizCounter.exists()) {
			read();
		}
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
				//As this method writes to player, it must acquire a lock.
				//Prevents othre user writing to players a the same time.
				//Also, prevents other users from reading an incomplete set.
				synchronized (players) {
					players.add(new PlayerImpl(userName));
					flushPlayers();
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
	
	public void addNewAttempt(Attempt attempt, int quizId)
		throws RemoteException, IllegalArgumentException {
			//Checks whether the quizId exists.
			//If false, an exception is thrown.
			if (!quizIdExists(quizId)) {
				throw new IllegalArgumentException();
			} else {
				//Adds a new attemopt to the relevant quiz so must acquie a lock.
				//Prevents other users from reading or writing to quizzes.
				synchronized (quizzes) {
					//Creates an iterator to search the list.
					ListIterator<Quiz> iterator = quizzes.listIterator();
					boolean finished = false;
					while (!finished) {
						if (iterator.hasNext()) {
							Quiz temp = iterator.next();
							if (temp.getQuizId() == quizId) {
								temp.addNewAttempt(attempt);
								finished = true;
							}
						} else {
							finished = true;
						}
					}
					flushQuizzes();
				}
			}
	}
		
	public int addNewQuiz(Quiz quiz) 
		throws RemoteException, IllegalArgumentException, NullPointerException {
			//Initialises quizId to a negative nuber which is invalid 
			//therefore preventing interference with user input.
			int quizId = -10;
			//Checks if quiz is null.
			//If true, an exception is thrown.
			if (quiz.equals(null)) {
				throw new NullPointerException();
			//Checks if the author is a registered Player.
			//If false, an exception is thrown.
			} else if (!userNameExists(quiz.getAuthor())) {
				throw new IllegalArgumentException();
			} else {
				//Adds a new attemopt to the relevant quiz so must acquie a lock.
				//Prevents other users from reading or writing to quizzes.
				synchronized (quizzes) {
					//Calls nextId() to return the next unique quizId.
					quizId = nextId();
					quiz.setQuizId(quizId);
					quizzes.add(quiz);
					flushQuizzes();
				}
			}
			return quizId;
	}
	
	/**
	 * A method which controls the quizIdCounter.
	 * Returns the current value of quizIdCounter,
	 * then increments the counter by 1.
	 * Has private access because the method should only be accessed within addNewQuiz(Quiz).
	 * Synchronized method because it increments quizIdCounter. 
	 * Two threads should not be able to access this concurrently.
	 *
	 * @return a unique quizId.
	 */
	private synchronized int nextId() {
		int result = quizIdCounter;
		quizIdCounter++;
		flushQuizCounter();
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
							//Locks quizzes to prvent conflicts.
							synchronized (quizzes) {
								iterator.remove();
								temp.terminate();
								quizzes.add(temp);
								finished = true;
								flushQuizzes();
							}
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
	
	/**
	 * Writes the contents of players to disk.
	 */
	private void flushPlayers() {
		FileOutputStream fos = null;
		ObjectOutputStream player = null;	
		//Locks players to prevent data changing during write to disk.
		synchronized (players) {
			try {	
				fos = new FileOutputStream("./players.data");
				player = new ObjectOutputStream(fos);
				player.writeObject(players);
			} catch (IOException e) {
				System.out.println("There was an error writing to disk. Contents of players were not written to disk");
			} finally {
				try {
					fos.close();
					player.close();
				} catch (IOException e) {
					System.out.println("There was an error writing to disk. Contents of players were not written to disk");
				}

			}
		}
	}
	
	/**
	 * Writes the contents of quizzes to disk.
	 */
	private void flushQuizzes() {
		FileOutputStream fos = null;
		ObjectOutputStream quiz = null;
		//Locks quizzes to prevent data being changed during write to disk.
		synchronized (quizzes) {
			try {	
				fos = new FileOutputStream("./quizzes.data");
				quiz = new ObjectOutputStream(fos);
				quiz.writeObject(quizzes);
			} catch (IOException e) {
				System.out.println("There was an error writing to disk.	Contents of quizzes were not written to disk");
			} finally {
				try {
				 	fos.close();
					quiz.close();
				} catch (IOException e) {
					System.out.println("There was an error writing to disk.	Contents of quizzes were not written to disk");
				}

			}
		}
	}

	/**
	 * Writes the contents of quizCounter to disk.
	 */
	private void flushQuizCounter() {
		FileOutputStream fos = null;
		ObjectOutputStream quizCounter = null;
		//Locks quizIdCounter to prevent data being changed during write to disk.
		synchronized (quizIdCounter) {
			try {	
				fos = new FileOutputStream("./quizIdCounter.data");
				quizCounter = new ObjectOutputStream(fos);
				quizCounter.writeObject(quizIdCounter);
			} catch (IOException e) {
				System.out.println("There was an error writing to disk.	Contents of quizIdCounter were not written to disk");
			} finally {
				try {
					fos.close();
					quizCounter.close();
				} catch (IOException e) {
					System.out.println("There was an error writing to disk.	Contents of quizIdCounter were not written to disk");
				}
			}
		}
	}
	
	/**
	 * Reads the contents of players.data, quizzes.data and quizIdCounter.data.
	 * Assigns the contents to the corresponding instance variables.
	 */
	private void read() {
		ObjectInputStream input = null;
		FileInputStream fis = null;
		try {
			//Reads files as the Object superclass then downcasts to their correct class.
			fis = new FileInputStream("./players.data");
			input = new ObjectInputStream(fis);
			Object temp = input.readObject();
			players = (TreeSet<Player>) temp;
			fis = new FileInputStream("./quizzes.data");
			input = new ObjectInputStream(fis);
			temp = input.readObject();
			quizzes = (LinkedList<Quiz>) temp;
			fis = new FileInputStream("./quizIdCounter.data");
			input = new ObjectInputStream(fis);
			temp = input.readObject();
			quizIdCounter = (Integer) temp;
		} catch (Exception e) {
			System.out.println("Error reading data files.");
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				input.close();
			} catch (Exception e) {
				System.out.println("Error reading data files.");
				e.printStackTrace();
			}
		}
	}
	
	public Leaderboard getTop3(int quizId) throws RemoteException, IllegalArgumentException {
		//Checks that quizId exists.
		//If false throws an exception.
		if (!quizIdExists(quizId)) {
			throw new IllegalArgumentException();
		} else {
			Leaderboard result = new LeaderboardImpl();
			Quiz quiz = getQuiz(quizId);
			Leaderboard lb = quiz.getLeaderboard();
			int size = lb.size();
			//If the size of the leaderboard is greater than 3, size is set to back to 3.
			if (size > 3) {
				size = 3;
			}
			for (int i = 0; i < size; i++) {
				result.add(lb.get(i));
			}
			return result;
		}
	}
}
