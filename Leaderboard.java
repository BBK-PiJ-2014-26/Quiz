import java.util.List;

/**
 * The Leaderboard class organises Attempts at a quiz using a List structure.
 * Attempts are organised by score with the highest score occupying the smallest index 
 * and the lowest score occupying the largest index. For example, there are two scores 8 and 4, 
 * 8 would occupy index 0, and 4 would occupy index 1.
 */
public interface Leaderboard<A extends Attempt> extends List {

}
 
