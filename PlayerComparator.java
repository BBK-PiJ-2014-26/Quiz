import java.util.Comparator;

/**
 * A comparator object to be used for organising Players on the QuizService.
 */
public class PlayerComparator implements Comparator<Player> {
	
	public int compare(Player p1, Player p2) {
		String p1UserName = p1.getUserName();
		String p2UserName = p2.getUserName();
		return p1UserName.compareTo(p2UserName);
	}
}
