import java.util.LinkedList;

/**
 * Implements the interface Leaderboard.
 * Extends a LinkedList, but overrides the add method to ensure Attempts are organised by score.
 */
public class LeaderboardImpl extends LinkedList<Attempt> implements Leaderboard {
		
	public LeaderboardImpl() {
		super();
	}
	
	/**
	 * Overrides add(E) in LinkedList.
	 * Organised by score, highest score at index 0, 
	 * with scores then organised in descending order.
	 * 
	 * @param a the Attempt to be added to the leaderboard.
	 * @throws NullPointerException if a is null.
	 */
	@Override
	public boolean add(Attempt a) throws NullPointerException {
		boolean result = false;
		//Checks whether a is null.
		//If true, throws an exception.
		if (a.equals(null)) {
			throw new NullPointerException();
		//Checks whether the list is empty.
		//If true, a is added at index 0.		
		} else if (isEmpty()) {
			add(0, a);
			result = true;
		} else {
			//Cyles through leaderboard to find where to places this attempt.
			for (int i = 0; i < size(); i++) {
				Attempt temp = get(i);
				//Checks whether a's score is greater than or equal to the current Attempt's score.
				//If true, a is added to the Leaderboard at index i.
				if (a.getScore() >= temp.getScore()) {
					add(i, a);
					result = true;
				}
			}
		}
		return result;
	}
}
