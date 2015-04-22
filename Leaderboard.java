/**
 * The Leaderboard class organises Attempts at a quiz using a List structure.
 * Attempts are organised by score with the highest score occupying the smallest index 
 * and the lowest score occupying the largest index. For example, there are two scores 8 and 4, 
 * 8 would occupy index 0, and 4 would occupy index 1.
 */
public interface Leaderboard {

	/**
	 * Adds an Attempt to the Leaderboard.
	 * Ensures that the list is ordered by score.
	 *
	 * @param a is the Attempt to be added to the leaderboard.
	 * @throws NullPointerException if a is null.
	 */
	public void add(Attempt a) throws NullPointerException; 
	
	/**
	 * Return an Attempt from the leaderboard.
	 *
	 * @param index is an integer representing the place in the leaderboard. Index orgaised from 0 upwards.
	 * 0 representing the highest score and then descending from there.
	 * @return an Attempt from the Leaderboard at the specified index.
	 * @throws IndexOutOfBoundsException
	 */
	public Attempt get(int index) throws IndexOutOfBoundsException;	 
	
	/**
	 * @return true if leaderboard is empty.
	 */
	public boolean isEmpty();
	
	/**
	 * @return the size of a the leaderboard an integer.
	 */
	public int size();
}
 
