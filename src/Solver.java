import java.util.*;
/**
 * Solver.java
 * 
 * File: 
 * 		$Id: Solver.java,v 1.9 2014/08/07 12:49:01 rxl7906 Exp $
 * 
 * Revisions:
 * 		$Log: Solver.java,v $
 * 		Revision 1.9  2014/08/07 12:49:01  rxl7906
 * 		only square chess board can be played
 * 		rectangles don't work
 *
 * 		Revision 1.8  2014/08/06 21:33:26  rxl7906
 * 		test commit
 *
 * 		Revision 1.7  2014/08/05 23:29:24  rxl7906
 * 		Chess pieces work
 *
 * 		Revision 1.6  2014/08/05 16:41:37  rxl7906
 * 		commit
 *
 * 		Revision 1.5  2014/07/17 21:45:10  rxl7906
 * 		final commit
 *
 * 
 * Solver.java - The naive BFS solver
 * 
 * Solver class, that takes clock object as a puzzle
 * interface, and uses it to solve the using the 
 * Naive BFS solver. 
 * 
 * @author Robin rxl7906@rit.edu
 *			Lakshay Khatter
 */
public class Solver<E> {
	
	/**
	 * solution - list of solutions
	 */
	//private ArrayList<E> solution = new ArrayList<E>();
	
	/**
	 * This method is used to get the solution arraylist
	 * @return the solution arraylist
	 */
	/*public ArrayList<E> get_Solution() {
		return solution;
	}*/
	
	/**
	 * Solver: solves the given puzzle
	 * @param clock
	 */
	public  ArrayList<E> Solve (Puzzle<E> puzzle){
		
		// create an empty queue as an ArrayList<ArrayList<Integer>>
		ArrayList<ArrayList<E>> queue = new ArrayList<ArrayList<E>>();
		
		// create an ArrayList<Integer> of one element from the starting config
		ArrayList<E> current = new ArrayList<E>();
		
		// additions to implement memoization
		// hashset eliminates duplicates
		HashSet<E> visited = new HashSet<E>();
		ArrayList<E> solution = null;
		
		// enqueue it
		current.add(puzzle.getStart());
		queue.add(current);
		
		// additions to implement memoization
		visited.add(puzzle.getStart());
		
		
		// set found to whether the starting config is the goal config, or not
		boolean found = false;
		if (puzzle.isGoal(puzzle.getStart())) {
			found = true;
		}
		
		// while queue is not empty and not found
		while(!queue.isEmpty() && found!=true) {
			
			// dequeue the front element from the queue and set it to the current
			// curr = queue.remove(0);
/*			current = queue.get(0);
			queue.remove(0);*/
			
			//additions to implement memoization
			// dequeue the front element from the queue and set
			// it to ArrayList<Integer> solution
			solution = queue.remove(0);
			
			// for each neighbor of the last element in current
			// get the value at the [solution.size()-1] index
			ArrayList<E> neighbors = puzzle.getNeighbors(solution.get(solution.size() - 1));
			for (E neighbor : neighbors){
				
				// additions
				// check if visited do not contain neighbors
				// next has visited and neighbor configs
				if (!visited.contains(neighbors)) {
					ArrayList<E> next = new ArrayList<E>();
					for (E i : solution) {
						next.add(i);
					}
					next.add(neighbor); 
					//System.out.println(next);
					
					// checks if goal is found and sets arraylist<E>
					// to solution and found = true. 
					if (puzzle.isGoal(next.get(next.size() -1))) {
						solution = next;
						found = true;
						break;
					}else {
						// if goal not found, keep adding ArrayList next
						// to the queue to keep getting neighbors
						queue.add(next);
					}
					// add neighbors to the visited list
					visited.add(neighbor);
				/*// create an arraylist<integer> for the next config and make a copy of it
				ArrayList<Integer> next = new ArrayList<Integer>(curr);
				next.add(neighbor);
				
				// additions
				visited.add(neighbor);
				
				//if the next config is the goal
				//set curr to the next config
				//break out of the for loop
				if (next.get(next.size() - 1) == puzzle.getGoal()) {
					curr = next;
					found = true;
					break;
				}
				// else enqueue the next config
				else {
					queue.add(next);
				}*/
			}
		}
		}
		if (found) {
			//this.solution = current;
			return solution;
		}
		else {
			//System.out.println("There is not solution");
			return null;
		}
	}
}