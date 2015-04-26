import java.util.LinkedList;
import java.io.Serializable;

/**
 * Implements the interface Leaderboard.
 * Extends a LinkedList, but overrides the add method to ensure Attempts are organised by score.
 */
public class LeaderboardImpl extends LinkedList<Attempt> implements Leaderboard, Serializable {
		
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
		} else {
			//Adds the element to the begginning of the list.
			addFirst(a);
			bubbleSort();
			result = true;
		}
		return result;
	}
	
	/**
	 * Sorts the leaderboard using Bubble sort.
	 * Bubble sort should be efficient because the list is sorted ater every add operation.
	 * Therefore, the list will be mostly sorted.
	 */
	private void bubbleSort() {
		for (int i = 0; i < (size() - 1); i++) {
			for (int j = i; j < (size() - 1); j++) {
			Attempt a = get(j);
			Attempt b = get(j + 1);
				//If a's score is less than or equal to b,
				//a and b are swapped.
				if (a.getScore() <= b.getScore()) {
					swap(j, j + 1);
				}
			}
		}
	}
	
	/**
	 * Swaps two elements in the leaderboard.
	 *
	 * @param i the index repsenting the left element where left should be greater than right.
	 * @param j the index representing the right element.
	 */
	private void swap(int i, int j) {
		Attempt a = remove(i);
		add(j, a);
	}
}
