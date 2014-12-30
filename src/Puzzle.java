import java.util.*;

/*
 * Puzzle.java
 * 
 * File: 
 * 		$Id: Puzzle.java,v 1.7 2014/08/05 16:41:36 rxl7906 Exp $
 * 
 * Revisions:
 * 		$Log: Puzzle.java,v $
 * 		Revision 1.7  2014/08/05 16:41:36  rxl7906
 * 		commit
 *
 * 		Revision 1.6  2014/07/17 21:45:10  rxl7906
 * 		final commit
 *
 * 
 * Puzzle.java - The puzzle interface
 * 
 * An interface to a Puzzle. It contains the routines
 * necessary for accessing the start config, checking if the
 * given config ist he goal, as well as generating new 
 * neighboring configs. The puzzle interface incorporates
 * using generic methods to account for different data
 * structures that a puzzle can handle. 
 * 
 * @authors Robin Li rxl7906@rit.edu
 * 			Lakshay Khatter
 */			
public interface Puzzle<E>{
	
	/**
	 * Get the starting config for puzzle
	 * E helps incorporate both int and arraylist<integer> 
	 * structures for clock and water puzzle 
	 * @return the starting config
	 */
	public E getStart();
	
	/**
	 * Checks if the given config is the goal. 
	 * Config can be int or arraylist<integer> structures
	 * for clock and water puzzle
	 * @return the goal config
	 */
	public boolean isGoal(E config);
	
	/**
	 * For an incoming config, generate and return
	 * all direct neighbors to this config.
	 * Given config can be int or arraylist<integer> structures
	 * for clock and water puzzle. 
	 * @param config - the incoming config
	 * @return the collection of neighbor configs
	 */
	public ArrayList<E> getNeighbors(E config);
}
