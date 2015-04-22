import java.util.LinkedList;

/**
 * Implements the interface Leaderboard.
 * Extends an ArrayList, but overrides the add method to ensure Attempts .
 */
public class LeaderboardImpl {
	/**
	 * A LinkedList to hold Attempts.
	 * Attempts are organised by score.
	 *
	.* NB. I'm using composition rather than inheritance. When I tried inheritance,
	 * it led to some unholy Memory problems. Some internet research
	 * suggests extending classes from the standard Java libraries is not recommended.
	 */
	private LinkedList<Attempt> leaderboard;
		
	public LeaderboardImpl() {
		leaderboard = new LinkedList<Attempt>();
	}
	
	public void add(Attempt a) throws NullPointerException {
		//Checks whether a is null.
		//If true, throws an exception.
		if (a.equals(null)) {
			throw new NullPointerException();
		//Checks whether the list is empty.
		//If true, a is added at index 0.		
		} else if (leaderboard.isEmpty()) {
			leaderboard.add(0, a);
		} else {
			//Cyles through leaderboard to find where to places this attempt.
			for (int i = 0; i < leaderboard.size(); i++) {
				Attempt temp = leaderboard.get(i);
				//Checks whether a's score is greater than or equal to the current Attempt's score.
				//If true, a is added to the Leaderboard at index i.
				if (a.getScore() >= temp.getScore()) {
					leaderboard.add(i, a);
				}
			}
		}
	}
	
	public Attempt get(int index) throws IndexOutOfBoundsException {
		Attempt result = null;
		//Checks whether the index argument is out of bounds.
		//If true, an exception is thrown.
		if (index < 0 || index >= leaderboard.size()) { 
			throw new IndexOutOfBoundsException();
		} else {
			result = leaderboard.get(index);
		}
		return result;
	}
	
	public boolean isEmpty() {
		boolean result = leaderboard.isEmpty();
		return result;
	}
	
	public int size() {
		int result = leaderboard.size();
		return result;
	}
}
