import java.util.*;

/**
 * Clock.java
 * 
 * File: 
 * 		$Id: Clock.java,v 1.9 2014/08/05 16:41:39 rxl7906 Exp $
 * 
 * Revisions:
 * 		$Log: Clock.java,v $
 * 		Revision 1.9  2014/08/05 16:41:39  rxl7906
 * 		commit
 *
 * 		Revision 1.8  2014/07/17 21:45:10  rxl7906
 * 		final commit
 *
 * 
 * Clock.java - The main program source file
 * 
 * A clock class that has a main function. 
 * It implements the puzzle interface routines. 
 * 
 * The first puzzle to solve is a clock. THe clock only has an
 * hour hand, where N is the number of hours, which can be 
 * turned forward or backward one hour at a time (called a step).
 * 
 * The number of hours, an integer N
 * The start time, an integer from 1...N
 * The goal time, an integer from 1...N
 * 
 * @author Robin Li rxl7906@rit.edu
 *			Lakshay Khatter
 * 
 */
public class Clock implements Puzzle<Integer>{
	
	/**
	 * int start: the starting time
	 */
	private int start;
	
	/**
	 * int goal: the goal time
	 */
	private int goal;
	
	/**
	 * hours: the number of hours on the clock
	 */
	private int hours;
	
	/**
	 * Constructor for clock. With given parameters,
	 * set the values for start goal hours.
	 * @param start  	the starting time
	 * @param goal  	the goal time
	 * @param hours		the number of hours on the clock
	 */
	public Clock(int start, int goal, int hours) {
		this.start = start;
		this.goal = goal;
		this.hours = hours;
	}
	
	/**
	 * Main function to implement clock puzzle
	 * @param command line arguments in the order of
	 * hours, start, and goal
	 */
	public static void main(String[] args) {
		if (args.length == 3) {
			int hours = Integer.parseInt(args[0]);
			int start = Integer.parseInt(args[1]);
			int goal  = Integer.parseInt(args[2]);
			
			ArrayList<Integer> puzzle_Solution;
			
			//initialize clock, then pass it into Solver.
			//Then make an arraylist to hold the solution
			Puzzle<Integer> puzzle_Clock = new Clock(start, goal, hours);
			puzzle_Solution = new Solver<Integer>().Solve(puzzle_Clock);
			//ArrayList<Integer> puzzle_Solution = puzzle_Solver.get_Solution();
			
			if (puzzle_Solution != null) {
				for (int i = 0; i < puzzle_Solution.size(); i++){
					System.out.println("Step " + i + ": " + puzzle_Solution.get(i));
				}
			}
		}
		else {
			System.out.println("Usage: java Clock hours start goal");
		}
	}
	
	/**
	 * getStart gets starting time
	 * @return start
	 */
	public Integer getStart() {
		return this.start;
	}
	
	/**
	 * isGoal checks if the config is the goal time
	 * @return boolean true if it is the goal, false otherwise
	 */
	public boolean isGoal(Integer config) {
		if (this.goal == config) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * getNeighbors obtains the arraylist of neighbors
	 * @param config - current puzzle config
	 * @return arraylist<arraylist<integer>> of neighbors
	 */
	public ArrayList<Integer> getNeighbors (Integer config) {
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		//if config = hours, then add one less that config and add 1
		if (config == hours){
			neighbors.add(1);
			neighbors.add(hours - 1);
			return neighbors;
		}
		// if config = 1 you add the last hour(the highest hour) and 2
		if(config == 1) {
			neighbors.add(2);
			neighbors.add(hours);
			return neighbors;
		}
		//other cases add one less and one more than the config
		else {
			neighbors.add(config - 1);
			neighbors.add(config + 1);
			return neighbors;
		}
	}
}
